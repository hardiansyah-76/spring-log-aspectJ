package yukinari.log.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppMessages {

    public String hello(String name) {
        log.info("call AppMessages.hello()");
        return "hello " + name;
    }

    public String bye (String name) {
        log.info("call AppMessages.bye()");
        return "bye " + name;
    }

    public void test() {
        log.info("call AppMessagesTest()");
    }
}
