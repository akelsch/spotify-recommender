package de.htwsaar.spotifyrecommender.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class RatingEntityService {

    private final R2dbcEntityTemplate template;

    @Autowired
    public RatingEntityService(R2dbcEntityTemplate template) {
        this.template = template;
    }

    public Flux<RatingEntity> findAllRatings() {
        return template.select(RatingEntity.class)
                .matching(Query.empty()) // TODO filter by user
                .all();
    }

    public Mono<RatingEntity> createRating(RatingEntity ratingEntity) {
        return template.insert(ratingEntity); // TODO set user
    }
}
