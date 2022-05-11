package course.java.dao.impl;

import course.java.dao.IdGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

//@Component
//@Scope(SCOPE_PROTOTYPE)
public class LongIdGenerator implements IdGenerator<Long> {
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Long getNextId() {
        return sequence.incrementAndGet();
    }
}
