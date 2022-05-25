package course.java.tcp.chatclient;

public interface ChatClient {
    String login(ConnectionSettings settings);
    void sendMessage(String message);
    boolean logout();
    void addMessageListener(MessageListener listener); // Observer pattern
    void removeMessageListener(MessageListener listener);
}
