package se.bettercode.shorturl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
@AllArgsConstructor
public class AppConfig {

    private final ConfigProperties configProperties;
    private final MongoTemplate mongoTemplate;
    private final ServerPortService serverPortService;

    @Bean
    public ShortUrlFactory shortUrlFactory() {
        return new ShortUrlFactory(serverPortService, configProperties);
    }

    @Bean
    public MongoDAO mongoDAO() {
        return new MongoDAO(mongoTemplate);
    }
}
