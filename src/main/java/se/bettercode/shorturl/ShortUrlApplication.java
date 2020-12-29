package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

@SpringBootApplication
@ConfigurationPropertiesScan("se.bettercode.shorturl")
@RequiredArgsConstructor
public class ShortUrlApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ShortUrlApplication.class);

    private final ConfigurableEnvironment env;
    private final ConfigProperties configProperties;
    private final ShortUrlRepository repository;

    @Value("${spring.data.mongodb.uri}")
    private String databaseURI;

    public void run(String... args) throws Exception {
        LOG.info("Message: " + configProperties.getMessage());
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            for (ShortUrl u : repository.findAll()) {
                LOG.debug(u.toString());
            }
        }
        LOG.debug("Database URI: " + databaseURI);
        LOG.debug("Short URLs in DB: " + repository.count());
    }

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

}
