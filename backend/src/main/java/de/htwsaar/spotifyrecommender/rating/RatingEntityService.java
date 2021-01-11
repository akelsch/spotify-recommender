package de.htwsaar.spotifyrecommender.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class RatingEntityService {

    private final RatingEntityRepository repository;

    public Flux<RatingEntity> findAllRatings(String userId) {
        return repository.findAllByUserId(userId);
    }

    public Mono<RatingEntity> createRating(RatingEntity ratingEntity) {
        return repository.save(ratingEntity);
    }

    public Mono<RatingEntity> updateRating(RatingEntity ratingEntity, long id) {
        return repository.save(ratingEntity.withId(id));
    }
}
