package se.bettercode.shorturl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShortUrlTest {

    @Test
    public void create() {
        ShortUrl shortUrl = new ShortUrl("https://myfullhostname.com", "http://a.bettercode.se/asdfgh");
        Assertions.assertEquals("ShortUrl(id=null, fullUrl=https://myfullhostname.com, shortUrl=http://a.bettercode.se/asdfgh, redirectCount=0)", shortUrl.toString());
    }

}