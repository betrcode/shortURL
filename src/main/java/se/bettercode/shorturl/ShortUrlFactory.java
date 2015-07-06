package se.bettercode.shorturl;

import java.util.UUID;

public class ShortUrlFactory {

    public static int STARTING_REDIRECT_COUNT = 0;

    private String baseUrl;

    public ShortUrlFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ShortUrl makeShortUrl(String url) {
        return new ShortUrl(url, baseUrl + "/" + getShortKey(), STARTING_REDIRECT_COUNT);
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
