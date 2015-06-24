package se.bettercode.shorturl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    private final Logger log = LoggerFactory.getLogger(WelcomeController.class);

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
        log.debug("Shortening URL: " + url);
        ShortUrl shortUrl = ShortUrlFactory.makeShortUrl(url);
        log.debug("Result: " + shortUrl.getShortUrl());
        this.message = shortUrl.getShortUrl();
        repository.save(shortUrl);
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("url", shortUrl.getShortUrl());
        model.put("fullurl", shortUrl.getFullUrl());
        return "welcome";
    }

}