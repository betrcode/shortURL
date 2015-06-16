package se.bettercode.shorturl;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShortUrlApplication.class)
public class ShortUrlRepositoryTests {

    @Autowired
    ShortUrlRepository repository;

    @Test
    public void saveAndRetrieveWorks() {
        repository.save(new ShortUrl("http://longurl.com/kjhsdkjfhdskjfds", "http://shrturl.nu/hej"));
        ShortUrl shortUrl = repository.findByShortUrl("http://shrturl.nu/hej");
        assertTrue(shortUrl.getFullUrl().equalsIgnoreCase("http://longurl.com/kjhsdkjfhdskjfds"));
    }
}
