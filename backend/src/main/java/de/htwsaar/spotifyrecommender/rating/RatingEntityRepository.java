package de.htwsaar.spotifyrecommender.rating;

import de.htwsaar.spotifyrecommender.rating.model.RatingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RatingEntityRepository extends ReactiveCrudRepository<RatingEntity, Long> {

    Flux<RatingEntity> findAllByUserId(String userId);

    Mono<Void> deleteByIdAndUserId(Long id, String userId);
}
