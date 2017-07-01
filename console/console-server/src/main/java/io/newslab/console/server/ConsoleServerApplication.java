package io.newslab.console.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "io.newslab")
@EnableJpaRepositories(basePackages = "io.newslab.persistence")
@EntityScan(basePackages = "io.newslab.persistence.model")
public class ConsoleServerApplication extends SpringBootServletInitializer {

    public static void main(String... args) {
        new ConsoleServerApplication()
                .configure(new SpringApplicationBuilder(ConsoleServerApplication.class))
                .run(args);
    }

}
