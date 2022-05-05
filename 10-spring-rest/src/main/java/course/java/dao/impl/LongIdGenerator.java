package course.java.dao.impl;

import course.java.dao.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class LongIdGenerator implements IdGenerator<Long> {
    private long nextId = 0;
    @Override
    public Long getNextId() {
        return ++ nextId;
    }
}
