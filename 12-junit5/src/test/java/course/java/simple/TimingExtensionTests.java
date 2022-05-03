package course.java.simple;

import course.java.extensions.TimingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
public class TimingExtensionTests {
    @Test
    void sleep20ms() throws Exception{
        Thread.sleep(20);
    }
    @Test
    void sleep50ms() throws Exception{
        Thread.sleep(50);
    }
}
