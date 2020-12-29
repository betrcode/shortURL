/**
 * Inspired by https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-data-mongodb/src/main/java/sample/data/mongo/CustomerRepository.java
 *
 */

package se.bettercode.shorturl;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
    List<ShortUrl> findAll();
    ShortUrl findByFullUrl(String fullUrl);
    ShortUrl findByShortUrl(String shortUrl);
}
