package io.newslab.console.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"io.newslab"})
public class ConsoleServerApplication extends SpringBootServletInitializer {

    public static void main(String... args) {
        new ConsoleServerApplication()
                .configure(new SpringApplicationBuilder(ConsoleServerApplication.class))
                .run(args);
    }

}
