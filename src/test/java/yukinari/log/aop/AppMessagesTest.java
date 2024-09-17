package yukinari.log.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yukinari.log.aop.service.AppMessages;

@SpringBootTest
public class AppMessagesTest {

    @Autowired
    private AppMessages appMessages;

    @Test
    void testMessagess() {
        Assertions.assertEquals("hello yuki", appMessages.hello("yuki"));
        Assertions.assertEquals("bye yuki", appMessages.bye("yuki"));

        appMessages.test();
    }
}
