package com.authentication.nubnasirauth.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConfig {

    private final Environment env;

    public FlywayConfig(final Environment env) {
        this.env = env;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(@Value("${spring.flyway.url}") String url,
                         @Value("${spring.flyway.user}") String user,
                         @Value("${spring.flyway.password}") String password
                         ) {

        return new Flyway(Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(url, user, password));
    }
}
