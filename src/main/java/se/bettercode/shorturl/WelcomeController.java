package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    private final ShortUrlRepository repository;
    private final MongoDAO mongoDAO;
    private final ShortUrlFactory shortUrlFactory;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("time", new Date());
        model.addAttribute("redirects", mongoDAO.getTotalRedirectSum());
        model.addAttribute("shortenedURLs", repository.count());
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String shortenURL(String url, Model model) {
        ShortUrl shortUrl = shortUrlFactory.makeShortUrl(url);
        LOG.info("Shortened URL: {} to: {}", url, shortUrl.getShortUrl());
        repository.save(shortUrl);
        model.addAttribute("time", new Date());
        model.addAttribute("url", shortUrl.getShortUrl());
        model.addAttribute("fullurl", shortUrl.getFullUrl());
        model.addAttribute("redirects", mongoDAO.getTotalRedirectSum());
        model.addAttribute("shortenedURLs", repository.count());
        return "welcome";
    }

}