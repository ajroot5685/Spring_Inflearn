package hello.itemservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanPrinterRunner implements CommandLineRunner {

    private final ApplicationContext context;

    public BeanPrinterRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("All beans in the application context:");
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}