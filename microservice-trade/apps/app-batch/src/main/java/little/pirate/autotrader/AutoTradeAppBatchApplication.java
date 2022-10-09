package little.pirate.autotrader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableBatchProcessing
@SpringBootApplication
public class AutoTradeAppBatchApplication {
    public static void main(String[] args) {
        int exit = SpringApplication.exit(new SpringApplicationBuilder(AutoTradeAppBatchApplication.class).run(args));
        System.exit(exit);
    }
}
