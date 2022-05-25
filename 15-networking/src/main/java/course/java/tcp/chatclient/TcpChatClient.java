package course.java.tcp.chatclient;

import course.java.tcp.server.TcpTimeServer;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
public class TcpChatClient implements ChatClient, Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ConnectionSettings settings;
    private InetAddress address;
    private volatile boolean canceled = false;

    public void cancel() {
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
    public void logout() {
        out.println("<END>");
        cancel();
    }

    @Override
    public void addMessageListener(MessageListener listener) {

    }

    @Override
    public void removeMessageListener(MessageListener listener) {

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) throws IOException {

    }
}

