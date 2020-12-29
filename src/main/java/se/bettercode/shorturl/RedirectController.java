package se.bettercode.shorturl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class RedirectController {

    private static final Logger LOG = LoggerFactory.getLogger(RedirectController.class);

    private final ShortUrlRepository repository;
    private final MongoDAO mongoDAO;

    @RequestMapping(value="???????", method = RequestMethod.GET)
    public void redirect(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ShortUrl shortUrl = findShortUrl(request);

        if (shortUrl != null) {
            LOG.debug("Hit!");
            redirect(httpServletResponse, shortUrl);
        } else {
            LOG.debug("Miss!");
            httpServletResponse.sendRedirect("/");
        }

    }

    private ShortUrl findShortUrl(HttpServletRequest request) {
        String currentUrl = request.getRequestURL().toString();
        LOG.debug("Searching for: " + currentUrl);
        return repository.findByShortUrl(currentUrl);
    }

    private void redirect(HttpServletResponse httpServletResponse, ShortUrl shortUrl)  throws IOException {
        mongoDAO.incrementRedirectCounter(shortUrl);
        String destinationUrl = shortUrl.getFullUrl();
        LOG.debug("Going to redirect to: " + destinationUrl);
        httpServletResponse.sendRedirect(destinationUrl);
    }

}
