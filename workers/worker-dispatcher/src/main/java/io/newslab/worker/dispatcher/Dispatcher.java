package io.newslab.worker.dispatcher;

import io.newslab.persistence.repository.RssFeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "io.newslab")
@EnableJpaRepositories(basePackages = "io.newslab")
@EntityScan(basePackages = "io.newslab")
public class Dispatcher {

    private final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    @PostConstruct
    public void logProps() {
        logger.info("Dispatcher started");
    }


    public static void main(String... args) {
        SpringApplication.run(Dispatcher.class, args);
    }
}
