package course.java.properties_xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesToXmlDemo {

    public static void printProperties(Properties props) {
//		props.list(System.out);
        Enumeration<Object> keys = props.keys();
        while (keys.hasMoreElements()) {
            String k = (String) keys.nextElement();
            System.out.println(k + " = " + props.getProperty(k, "not defined"));
        }

        System.out.println();
        Set<Map.Entry<Object, Object>> entries = props.entrySet();
        for (Map.Entry<Object, Object> e : entries) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        URL appConfigPath = PropertiesToXmlDemo.class.getClassLoader()
                .getResource("app.properties");

        System.out.println("Path: " + appConfigPath);

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath.getPath()));

        appProps.setProperty("date", LocalDate.now().toString());

        Path configPath;
        try {
            configPath = Paths.get(appConfigPath.toURI());
            Path xmlPath = configPath.resolve("../../../application.xml").normalize();
            appProps.setProperty("config.path", xmlPath.toString());
            printProperties(appProps);

            appProps.storeToXML(new FileOutputStream(xmlPath.toString()),
                    "application configuration", StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
