package io.newslab.persistence;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TestConfig {

    @Bean
    public FlywayMigrationStrategy cleanMigrateStrategy() {
        return new FlywayMigrationStrategy() {
            public void migrate(Flyway flyway) {
                flyway.clean();
                flyway.migrate();
            }
        };
    }

}
