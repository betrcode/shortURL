package se.bettercode.shorturl;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "integration-test")
public class ShortUrlIntegrationTest {

    @Autowired
    private ServletWebServerApplicationContext webServerAppContext;

    @BeforeEach
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        // TODO: There must be a nicer way to do this
        RestAssured.port = webServerAppContext.getWebServer().getPort();
    }

    @Test
    public void welcomePage() {
        RestAssured.given()
                .when()
                .get("/")
                .then()
                .statusCode(200);
    }

    @Test
    public void createShortUrlAndTryIt() {
        final String longUrl = "https://www.uu.se";
        final ExtractableResponse<Response> response = RestAssured.given()
                .when()
                .param("url", longUrl)
                .post("/")
                .then()
                .statusCode(200).extract();

        String shortUrl = response.header("X-ShortUrl");
        Assertions.assertTrue(shortUrl.startsWith("http"));

        RestAssured.given()
                .when().redirects().follow(false)
                .get(shortUrl)
                .then()
                .statusCode(302)
                .header("Location", Matchers.equalTo(longUrl));
    }
}
