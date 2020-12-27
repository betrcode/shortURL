package se.bettercode.shorturl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

@Controller
public class WelcomeController {

    private final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @Value("${server.address:localhost}")
    private String serverAddress = "localhost";

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${application.baseurl}")
    private String applicationBaseURL;

    private ShortUrlFactory shortUrlFactory;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("time", new Date());
        model.addAttribute("message", this.message);
        model.addAttribute("redirects", repository.getTotalRedirectSum());
        model.addAttribute("shortenedURLs", repository.count());
        return "welcome";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String shortenURL(String url, Model model) {
        log.debug("Shortening URL: " + url);
        shortUrlFactory = new ShortUrlFactory(applicationBaseURL);
        ShortUrl shortUrl = shortUrlFactory.makeShortUrl(url);
        log.debug("Result: " + shortUrl.getShortUrl());
        repository.save(shortUrl);
        model.addAttribute("time", new Date());
        model.addAttribute("message", this.message);
        model.addAttribute("url", shortUrl.getShortUrl());
        model.addAttribute("fullurl", shortUrl.getFullUrl());
        model.addAttribute("redirects", repository.getTotalRedirectSum());
        model.addAttribute("shortenedURLs", repository.count());
        return "welcome";
    }

}