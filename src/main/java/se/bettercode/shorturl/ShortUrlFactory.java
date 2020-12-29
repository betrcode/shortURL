package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ShortUrlFactory {

    private final ServerPortService serverPortService;
    private final ConfigProperties configProperties;

    public ShortUrl makeShortUrl(String url) {
        if (configProperties.getBaseurl().contains("localhost")) {
            // In integration-test, we use a random port, which is why we need this
            return new ShortUrl(url, configProperties.getBaseurl() + ":" + serverPortService.getPort() + "/" + getShortKey());

        }
        // No fiddling with ports in production
        return new ShortUrl(url, configProperties.getBaseurl() + "/" + getShortKey());
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
