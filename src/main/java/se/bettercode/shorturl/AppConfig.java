package se.bettercode.shorturl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class AppConfig {

    private final ConfigProperties configProperties;

    @Bean
    public ShortUrlFactory shortUrlFactory() {
        return new ShortUrlFactory(configProperties);
    }
}
