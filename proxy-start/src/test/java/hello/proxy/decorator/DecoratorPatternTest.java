package hello.proxy.decorator;

import hello.proxy.decorator.code.DecoratorPatternClient;
import hello.proxy.decorator.code.MessageDecorator;
import hello.proxy.decorator.code.RealComponent;
import hello.proxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        MessageDecorator messageDecorator = new MessageDecorator(new RealComponent());
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2() {
        TimeDecorator timeDecorator = new TimeDecorator(new MessageDecorator(new RealComponent()));
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
