package se.bettercode.shorturl;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortUrlApplication implements CommandLineRunner {

    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message}")
    private String message;

    @Value("${spring.data.mongodb.uri}")
    private String databaseURI;

    @Autowired
    private VelocityEngine engine;

    private final Logger log = LoggerFactory.getLogger(ShortUrlApplication.class);


    public void run(String... args) throws Exception {
        /*
        for (ShortUrl u : repository.findAll()) {
            log.debug(u.toString());
        }
        */
        log.debug("Database URI: " + databaseURI);
        log.debug("Short URLs in DB: " + repository.count());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

}
