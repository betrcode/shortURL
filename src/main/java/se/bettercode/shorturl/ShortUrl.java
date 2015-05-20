package se.bettercode.shorturl;

import org.springframework.data.annotation.Id;

public class ShortUrl {

    @Id
    private String id;

    private String fullUrl;
    private String shortUrl;

    public ShortUrl() {}

    public ShortUrl(String fullUrl, String shortUrl) {
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "ShortUrl[id=%s, fullUrl='%s', shortUrl='%s']",
                id, fullUrl, shortUrl);
    }
}
