package course.java.dao.impl;

import course.java.dao.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class LongIdGenerator implements IdGenerator<Long> {
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Long getNextId() {
        return sequence.incrementAndGet();
    }
}
