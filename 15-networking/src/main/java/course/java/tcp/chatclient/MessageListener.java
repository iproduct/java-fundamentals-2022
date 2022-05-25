package course.java.tcp.chatclient;

// implements Observer pattern
public interface MessageListener {
    void onMessage(String message);
    void onError(String error);
}
