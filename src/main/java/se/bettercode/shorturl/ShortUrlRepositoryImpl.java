package se.bettercode.shorturl;


import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShortUrlRepositoryImpl implements ShortUrlRepositoryCustom {

    private final MongoOperations operations;

    private final Logger log = LoggerFactory.getLogger(ShortUrlRepositoryImpl.class);

    @Override
    public Integer getTotalRedirectSum() {
        AggregationResults<SumResult> results = operations.aggregate(
                Aggregation.newAggregation(ShortUrl.class,
                        match(Criteria.where("redirectCount").gt(0)),
                        Aggregation.group().sum("redirectCount").as("total")
                ).withOptions(new AggregationOptions.Builder().cursor(new Document()).build()), SumResult.class
        );
        // TODO: Fix this properly
        if (results.getMappedResults().isEmpty()) {
            return 0;
        }
        return results.getUniqueMappedResult().getTotal();
    }
}
