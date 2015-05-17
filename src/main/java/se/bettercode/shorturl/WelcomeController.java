package se.bettercode.shorturl;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

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
        System.out.println("Result: " + getShortKey(url));
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }
    
    private String getShortKey(String url) {
        Random random = new Random(System.currentTimeMillis());
        return "" + random.nextInt();
    }

}