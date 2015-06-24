package se.bettercode.shorturl;


import java.util.UUID;

public class ShortUrlFactory {

    public static ShortUrl makeShortUrl(String url) {
        return new ShortUrl(url, "http://localhost:8080/" + getShortKey());
    }

    static private String getShortKey() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

}
