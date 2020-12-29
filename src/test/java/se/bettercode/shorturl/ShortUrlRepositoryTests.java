package se.bettercode.shorturl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;
import java.util.stream.IntStream;


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
        Assertions.assertTrue(mongoDAO.getTotalRedirectSum() >= 0L);
    }

    @Test
    public void incrementWorksWhenCalledInParallel() {
        final String shortenedUrl = "http://shrturl.nu/" + UUID.randomUUID().toString();
        repository.save(new ShortUrl("http://longurl.com/incrementor2", shortenedUrl, 0));

        // Increment the counter in parallel to make sure we update it atomically
        ShortUrl shortUrl = repository.findByShortUrl(shortenedUrl);
        IntStream.range(0, 100).parallel().forEach(i -> mongoDAO.incrementRedirectCounter(shortUrl));

        ShortUrl shortUrlResult = repository.findByShortUrl(shortenedUrl);
        Assertions.assertEquals(100, shortUrlResult.getRedirectCount());
    }
}
