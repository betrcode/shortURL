package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
public class ShortUrlFactory {

    private final ConfigurableEnvironment env;
    private final ServerPortService serverPortService;
    private final ConfigProperties configProperties;

    public ShortUrl makeShortUrl(String url) {
        if (Arrays.asList(env.getActiveProfiles()).equals(Collections.singletonList("default"))) {
            // No fiddling with ports in production
            return new ShortUrl(url, configProperties.getBaseurl() + "/" + getShortKey());
        }
        // In integration-test, we use a random port, which is why we need this
        return new ShortUrl(url, configProperties.getBaseurl() + ":" + serverPortService.getPort() + "/" + getShortKey());
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
