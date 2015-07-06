/**
 * Inspired by https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-data-mongodb/src/main/java/sample/data/mongo/CustomerRepository.java
 *
 */

package se.bettercode.shorturl;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
    ShortUrl findByFullUrl(String fullUrl);
    ShortUrl findByShortUrl(String shortUrl);
    Integer getTotalRedirectSum();
}
