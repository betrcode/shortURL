package se.bettercode.shorturl;

import lombok.Getter;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * This may not be a great solution in production where we might have load balancers etc
 */
@Service
@Getter
public class ServerPortService {
    private int port;

    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        port = event.getWebServer().getPort();
    }
}
