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

    public Mono<ServerResponse> getRating(ServerRequest serverRequest) {

        return ratingEntityService.findAllRatings().collectList()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> postRating(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> putRating(ServerRequest serverRequest) {
        return null;
    }

}
