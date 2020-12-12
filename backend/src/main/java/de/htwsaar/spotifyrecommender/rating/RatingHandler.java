package de.htwsaar.spotifyrecommender.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RatingHandler {

    private final RatingEntityService ratingEntityService;

    public Mono<ServerResponse> getRating(ServerRequest request) {
        return ratingEntityService.findAllRatings()
                .collectList()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> postRating(ServerRequest request) {
        return request.bodyToMono(RatingEntity.class)
                .flatMap(ratingEntityService::createRating)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> putRating(ServerRequest request) {
        return null;
    }

}
