package se.bettercode.shorturl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShortUrlApplication.class)
//@WebIntegrationTest("spring.data.mongodb.uri=mongodb://heroku_9tq1j2vq:gu5b3tsi24rim096igukmqo1l0@ds059908.mongolab.com:59908/heroku_9tq1j2vq")
@WebIntegrationTest("spring.data.mongodb.uri=mongodb://localhost:27018/test")
public class ShortUrlRepositoryTests {

    @Autowired
    ShortUrlRepository repository;

    @Test
    public void saveAndRetrieveWorks() {
        repository.save(new ShortUrl("http://longurl.com/kjhsdkjfhdskjfds", "http://shrturl.nu/hej", 0));
        ShortUrl shortUrl = repository.findByShortUrl("http://shrturl.nu/hej");
        assertTrue(shortUrl.getFullUrl().equalsIgnoreCase("http://longurl.com/kjhsdkjfhdskjfds"));
    }

    @Test
    public void getTotalRedirectSumWorks() {
        Integer total = repository.getTotalRedirectSum();
        assertTrue(total.intValue()>=0);
    }
}
