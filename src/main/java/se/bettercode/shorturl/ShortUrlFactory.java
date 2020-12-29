package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ShortUrlFactory {

    public static int STARTING_REDIRECT_COUNT = 0;

    private final ConfigProperties configProperties;

    public ShortUrl makeShortUrl(String url) {
        return new ShortUrl(url, configProperties.getBaseurl() + "/" + getShortKey(), STARTING_REDIRECT_COUNT);
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
