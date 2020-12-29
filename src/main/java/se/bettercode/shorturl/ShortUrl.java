package se.bettercode.shorturl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ShortUrl {

    @Id
    private String id;

    @NonNull
    private String fullUrl;
    @NonNull
    private String shortUrl;
    @NonNull
    private long redirectCount;

    public ShortUrl(String fullUrl, String shortUrl) {
        this(fullUrl, shortUrl, 0);
    }

}
