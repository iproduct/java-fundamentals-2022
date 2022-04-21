package course.java.observer.impl;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class Event<T> {
    private Object source;
    private T payload;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Event(T payload) {
        this.payload = payload;
    }

    public Event(Object source, T payload) {
        this.source = source;
        this.payload = payload;
    }

    public Event(Object source, T payload, LocalDateTime timestamp) {
        this.source = source;
        this.payload = payload;
        this.timestamp = timestamp;
    }
}
