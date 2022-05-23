package course.java;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Dnslookup {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] addr = InetAddress.getAllByName("yahoo.com");
        System.out.println("IP addresses of 'yahoo.com:");
        Arrays.stream(addr).forEach(a -> {
            System.out.printf("%s -> %s%n", a, a.getCanonicalHostName());
        });

    }
}
