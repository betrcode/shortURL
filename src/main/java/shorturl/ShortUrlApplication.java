package shorturl;

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
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }
}
