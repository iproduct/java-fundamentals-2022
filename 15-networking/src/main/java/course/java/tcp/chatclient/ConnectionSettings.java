package course.java.tcp.chatclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectionSettings {
    private String host;
    private int port;
    private String nickname;
}
