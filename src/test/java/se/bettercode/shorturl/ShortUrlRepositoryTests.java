package se.bettercode.shorturl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ShortUrlRepositoryTests {

    @Autowired
    ShortUrlRepository repository;

    @Autowired
    MongoDAO mongoDAO;

    @Test
    public void saveAndRetrieveWorks() {
        final String shortenedUrl = "http://shrturl.nu/" + UUID.randomUUID().toString();
        repository.save(new ShortUrl("http://longurl.com/kjhsdkjfhdskjfds", shortenedUrl, 0));
        ShortUrl shortUrl = repository.findByShortUrl(shortenedUrl);
        Assertions.assertTrue(shortUrl.getFullUrl().equalsIgnoreCase("http://longurl.com/kjhsdkjfhdskjfds"));
    }

    @Test
    public void getTotalRedirectSumWorks() {
        Assertions.assertTrue(mongoDAO.getTotalRedirectSum() >= 0);
    }
}
