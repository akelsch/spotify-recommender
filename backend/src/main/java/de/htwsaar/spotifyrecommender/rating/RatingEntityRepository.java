package de.htwsaar.spotifyrecommender.rating;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RatingEntityRepository extends ReactiveCrudRepository<RatingEntity, Long> {

    Flux<RatingEntity> findAllByUserId(String userId);
}
