package course.java.tcp.client;

import course.java.tcp.server.TcpTimeServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class TcpTimeClient {
    public static final List<String> ZONES = List.of("Europe/Sofia", "Europe/Rome", "Europe/Paris",
            "Europe/Berlin", "Europe/Brussels", "Europe/London", "Asia/Shanghai", "Asia/Tokyo");

    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(addr, TcpTimeServer.PORT)) {
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            for (var zoneStr : ZONES) {
                out.println(zoneStr);
                String timeStr = in.readLine();
                System.out.println("Time response: " + timeStr);
            }
            out.println("<END>");
            System.out.println("Client closing.");
        }
    }
}

