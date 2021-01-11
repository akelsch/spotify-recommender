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

    public Mono<ServerResponse> queryRatings(ServerRequest request) {
        return request.principal()
                .flatMapMany(p -> ratingEntityService.findAllRatings(p.getName())) // TODO use id instead of name
                .collectList()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> createRating(ServerRequest request) {
        return request.bodyToMono(RatingEntity.class)
                .zipWith(request.principal())
                .map(t -> t.getT1().withUserId(t.getT2().getName()))
                .flatMap(ratingEntityService::createRating)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> updateRating(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return request.bodyToMono(RatingEntity.class)
                .zipWith(request.principal())
                .map(t -> t.getT1().withUserId(t.getT2().getName()))
                .flatMap(ratingEntity -> ratingEntityService.updateRating(ratingEntity, id))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

}
