package se.bettercode.shorturl;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String shortenURL(String url, Map<String, Object> model) {
        System.out.println("Shortening URL: " + url);
        ShortUrl shortUrl = getRandomShortUrl(url);
        System.out.println("Result: " + shortUrl.getShortUrl());
        this.message = shortUrl.getShortUrl();
        repository.save(shortUrl);
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("url", shortUrl.getShortUrl());
        model.put("fullurl", shortUrl.getFullUrl());
        return "welcome";
    }

    private ShortUrl getRandomShortUrl(String url) {
        return new ShortUrl(url, "http://localhost:8080/" + getShortKey(url));
    }

    private String getShortKey(String url) {
        Random random = new Random(System.currentTimeMillis());
        return "" + random.nextInt();
    }
}