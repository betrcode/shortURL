package se.bettercode.shorturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {

    @Autowired
    private ShortUrlRepository repository;

    @RequestMapping(value="/**", method = RequestMethod.GET)
    public void redirect(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String currentUrl = request.getRequestURL().toString();
        System.out.println("Searching for: " + currentUrl);
        ShortUrl shortUrl = repository.findByShortUrl(currentUrl);
        System.out.println("Going to redirect to: " + shortUrl);
        String destinationUrl = shortUrl.getFullUrl();
        httpServletResponse.sendRedirect(destinationUrl);
    }

}
