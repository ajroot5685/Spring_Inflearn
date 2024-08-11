package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
//@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
//        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
