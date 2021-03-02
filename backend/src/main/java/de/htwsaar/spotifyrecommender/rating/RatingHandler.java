package de.htwsaar.spotifyrecommender.rating;

import de.htwsaar.spotifyrecommender.util.RequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RatingHandler {

    private final RatingEntityService ratingEntityService;
    private final RequestConverter requestConverter;

    public Mono<ServerResponse> queryRatings(ServerRequest request) {
        return ratingEntityService.findAllRatings()
                .collectList()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> createRating(ServerRequest request) {
        return request.bodyToMono(RatingEntity.class)
                .flatMap(ratingEntityService::createRating)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> updateRating(ServerRequest request) {
        Long id = requestConverter.pathVariable(request, "id", Long.class);
        return request.bodyToMono(RatingEntity.class)
                .flatMap(entity -> ratingEntityService.updateRating(entity, id))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> deleteRating(ServerRequest request) {
        Long id = requestConverter.pathVariable(request, "id", Long.class);
        return ratingEntityService.deleteRating(id)
                .then(ServerResponse.noContent().build());
    }

}
