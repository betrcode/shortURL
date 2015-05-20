package se.bettercode.shorturl;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Inspired by https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-data-mongodb/src/main/java/sample/data/mongo/CustomerRepository.java
 */
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
    ShortUrl findByFullUrl(String fullUrl);
    List<ShortUrl> findByShortUrl(String shortUrl);
}
