package se.bettercode.shorturl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ShortUrlRepositoryTests {

    @Autowired
    ShortUrlRepository repository;

    @Test
    public void saveAndRetrieveWorks() {
        repository.save(new ShortUrl("http://longurl.com/kjhsdkjfhdskjfds", "http://shrturl.nu/hej", 0));
        ShortUrl shortUrl = repository.findByShortUrl("http://shrturl.nu/hej");
        Assertions.assertTrue(shortUrl.getFullUrl().equalsIgnoreCase("http://longurl.com/kjhsdkjfhdskjfds"));
    }

    @Test
    public void getTotalRedirectSumWorks() {
        Integer total = repository.getTotalRedirectSum();
        Assertions.assertTrue(total >= 0);
    }
}
