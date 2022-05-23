package course.java.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PrintResourceByUrl {
    public static void main(String[] args) throws IOException {
        URL ulr = new URL("https://tools.ietf.org/rfc/rfc1118.txt");
        try(InputStream stream = ulr.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ) {
            String line;
            int count = 0;
            while((line = reader.readLine()) != null && count < 60) {
                System.out.println(line);
                count++;
            }
        }
    }
}
