package course.java.server.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class TcpTimeServer implements Runnable {
    public static final int PORT = 9090;

    private volatile boolean canceled = false;

    public void cancel() {
        this.canceled = true;
    }

    @Override
    public void run() {
        try(ServerSocket ssoc = new ServerSocket(PORT, -1,
                InetAddress.getByAddress(new byte[] {127, 0, 0, 1}))) {
            log.info("Time Server is listening for connections on: {}", ssoc);
            while(!canceled && !Thread.interrupted()) {
                try(Socket s = ssoc.accept()) {
                    log.info("Time Server connection accepted: {}", s);
                    var in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
                    // read request
                    String zoneStr = in.readLine();
                    log.info("Client time request for zone: {}", zoneStr);
                    ZoneId zoneId;
                    try {
                        zoneId = ZoneId.of(zoneStr);
                    } catch (DateTimeException ex) {
                        log.error("Invalid zone Id: " + zoneStr +". Taking Greenwich zone instead;", ex);
                        zoneId = ZoneId.of("Europe/London");
                    }
                    LocalDateTime now = LocalDateTime.now(zoneId);
                    out.printf("Current time: %s in Zone: %s%n", now.format(DateTimeFormatter.BASIC_ISO_DATE), zoneId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new TcpTimeServer().run();
    }
}
