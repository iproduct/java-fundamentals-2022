package course.java.tcp.chatserver;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
class Handler implements Runnable {
    private ChatServerTcp server;
    private final Socket  socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nickname;

    public Handler(ChatServerTcp server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            String message = "";
            // chat application protocol: 1) read nickname as first message
            nickname = in.readLine();
            log.info("User {} logged in.", nickname);

            // 2) Read chat messages until <END> message is received
            var finished = false;
            while(!finished) {
                // read request
                message = in.readLine();
                if (message.equals("<END>")) {
                    finished = true;
                    continue;
                }
                server.sendToAll(message);
            }
            // 3) Close session when <END> is received
            log.info("Closing session for user: {}", nickname);
            socket.close();
        } catch (IOException e) {
            log.error("Error reading/writing from/to TCP socket:", e);
            throw new RuntimeException(e);
        }
    }
}

public class ChatServerTcp {

    public void sendToAll(String message) {
    }
}
