package se.bettercode.shorturl;

import java.util.UUID;

public class ShortUrlFactory {

    private String baseUrl;

    public ShortUrlFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ShortUrl makeShortUrl(String url) {
        return new ShortUrl(url, baseUrl + "/" + getShortKey());
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
