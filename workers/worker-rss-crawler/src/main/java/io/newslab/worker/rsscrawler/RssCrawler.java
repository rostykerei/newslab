package io.newslab.worker.rsscrawler;

import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.repository.PublisherRepository;
import io.newslab.persistence.repository.impl.PublisherRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "io.newslab")
@EnableJpaRepositories(basePackages = "io.newslab")
@EntityScan(basePackages = "io.newslab")
public class RssCrawler {

    private final PublisherRepository publisherRepository;

    @Autowired
    public RssCrawler(PublisherRepositoryImpl publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public static void main(String... args) {
        SpringApplication.run(RssCrawler.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

           Publisher publisher = new Publisher();
            publisher.setTitle("test-publisher-qqq");

            publisherRepository.save(publisher);

            Optional<Publisher> publisher2 = publisherRepository.findByCanonicalName("test-publisher-qqq");

            System.out.println(publisher2.get().getTitle());

        };
    }
}
