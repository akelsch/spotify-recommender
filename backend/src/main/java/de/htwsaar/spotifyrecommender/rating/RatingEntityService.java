package de.htwsaar.spotifyrecommender.rating;

import de.htwsaar.spotifyrecommender.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RatingEntityService {

    private final RatingEntityRepository repository;

    public Flux<RatingEntity> findAllRatings() {
        return SecurityUtils.getUserId()
                .flatMapMany(repository::findAllByUserId);
    }

    public Mono<RatingEntity> createRating(RatingEntity ratingEntity) {
        return SecurityUtils.getUserId()
                .map(ratingEntity::withUserId)
                .flatMap(repository::save);
    }

    public Mono<RatingEntity> updateRating(RatingEntity ratingEntity, long id) {
        return SecurityUtils.getUserId()
                .map(ratingEntity::withUserId)
                .map(entity -> entity.withId(id)) // FIXME this allows to update other users ratings
                .flatMap(repository::save);
    }

    public Mono<Void> deleteRating(long id) {
        return SecurityUtils.getUserId()
                .flatMap(userId -> repository.deleteByIdAndUserId(id, userId));
    }

}
