package se.bettercode.shorturl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ConfigProperties {
    private String message;
    private String baseurl;
}
