package course.java.tcp.chatclient;

import course.java.tcp.chatserver.ChatServerTcp;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ChatClientTcp implements ChatClient, Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ConnectionSettings settings;
    private InetAddress address;
    private volatile boolean canceled = false;
    private List<MessageListener> listeners = new CopyOnWriteArrayList<>();

    protected void cancel() {
        canceled = true;
    }

    @Override
    public String login(ConnectionSettings settings) {
        this.settings = settings;
        try {
            address = InetAddress.getByName(settings.getHost());
            socket = new Socket(address, settings.getPort());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(settings.getNickname());
            log.info("User {} successfully logged in to server: {}", settings.getNickname(), socket);
            return null;
        } catch (UnknownHostException e) {
            log.error("Unknown chat server hostname:", e);
            return "Unknown chat server hostname: " + settings.getHost();
        } catch (IOException e) {
            log.error("Error connecting to chat server:", e);
            return "Error connecting to chat server: " + address + ":" + settings.getPort();
        }
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public boolean logout() {
        out.println("<END>");
        cancel();
        try {
            in.close();
            out.close();
            socket.close();
            return true;
        } catch (IOException e) {
            log.error("Error closing chat client:", e);
            return false;
        }
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeMessageListener(MessageListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        while (!canceled && !Thread.interrupted()) {
            try {
                String message = in.readLine();
                if (!canceled && !Thread.interrupted() && message != null)
                    for (var listener : listeners) {
                        listener.onMessage(message);
                    }
            } catch (IOException e) {
                log.error("Error receiving messge from server:", e);
                for (var listener : listeners) {
                    listener.onError("Error receiving messge from server:" + e.getMessage());
                }
            }
        }
        log.info("Chat client stopped: {}", settings);
    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(30, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your nickname:");
        var nickname = sc.nextLine();

        // create client
        var settings = new ConnectionSettings("localhost", ChatServerTcp.PORT, nickname);
        var client = new ChatClientTcp();

        // add message listener to listen for incoming message
        client.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(String message) {
                System.out.println("::: " + message);
            }

            @Override
            public void onError(String error) {
                System.out.println("!!! " + error);
            }
        });

        // login client -> in, out initilaized
        var errorMessage = client.login(settings);
        if (errorMessage != null) {
            System.out.println(errorMessage);
        } else {
            System.out.println("Successfully connected to the chat server: " + settings);
        }

        // run method in separate thread -> send message to MessageListeners
        var executor = Executors.newCachedThreadPool();
        executor.execute(client);
        System.out.print(">");

        // read messages from console and send them to chat server
        String message = "";
        while (!message.equals("quit")) {
            message = sc.nextLine();
            client.sendMessage(message);
        }

        // close client
        client.logout();
        shutdownAndAwaitTermination(executor);
    }
}

