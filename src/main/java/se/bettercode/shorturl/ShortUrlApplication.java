package se.bettercode.shorturl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ShortUrlApplication implements CommandLineRunner {

    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message}")
    private String message;

    @Autowired
    private VelocityEngine engine;
    
    public void run(String... args) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("time", new Date());
        model.put("message", this.message);
        System.out.println(VelocityEngineUtils.mergeTemplateIntoString(this.engine,
                "welcome.vm", "UTF-8", model));

        //repository.deleteAll();

        //Save a couple of short URLs
        repository.save(new ShortUrl("http://www.dn.se/ekonomi/partierna-overens-amorteringskrav-ska-inforas/", "http://betr.de/amoR"));
        repository.save(new ShortUrl("http://www.dn.se/sport/kan-fa-eget-forbund-efter-skandalen/", "http://betr.de/SkaN"));

        System.out.println("ShortUrls found with findAll():");
        System.out.println("-------------------------------");
        for (ShortUrl u : repository.findAll()) {
            System.out.println(u);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }
}
