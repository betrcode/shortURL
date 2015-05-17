package se.bettercode.shorturl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.bettercode.shorturl.ShortUrlApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShortUrlApplication.class)
@WebAppConfiguration
public class ShortUrlApplicationTests {

	@Test
	public void contextLoads() {
	}

}
