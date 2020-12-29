package se.bettercode.shorturl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class MongoDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDAO.class);

    private final MongoTemplate mongoTemplate;

    public long countAllShortUrls() {
        return mongoTemplate.estimatedCount("shortUrl");
    }

    public int getTotalRedirectSum() {
        final TypedAggregation<ShortUrl> typedAggregation = TypedAggregation.newAggregation(ShortUrl.class, Aggregation.group().sum("redirectCount").as("total"));
        final AggregationResults<SumResult> aggregate = mongoTemplate.aggregate(typedAggregation, SumResult.class);
        LOG.debug("Document: " + aggregate.getRawResults());
        return aggregate.getMappedResults().stream().findFirst().map(SumResult::getTotal).orElse(0);
    }

    public ShortUrl incrementRedirectCounter(ShortUrl shortUrl) {
        final Query query = new Query(Criteria.where("id").is(shortUrl.getId()));
        final Update update = new Update().inc("redirectCount", 1);
        final ShortUrl shortUrlResult = mongoTemplate.findAndModify(query, update, ShortUrl.class);
        LOG.debug("Increment result: " + shortUrlResult);
        return shortUrlResult;
    }

}
