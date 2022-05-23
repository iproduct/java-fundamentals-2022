package course.java.server.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

@Slf4j
public class TcpTimeServer implements Runnable {
    public static final int PORT = 9090;

    @Override
    public void run() {
        try(ServerSocket ssoc = new ServerSocket(PORT, -1,
                InetAddress.getByAddress(new byte[] {127, 0, 0, 1}))) {
            log.info("Time Server is listening for connections on: {}", ssoc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new TcpTimeServer().run();
    }
}
