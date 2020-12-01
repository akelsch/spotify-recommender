package de.htwsaar.spotifyrecommender.recent;

import de.htwsaar.spotifyrecommender.commons.ItemList;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RecentlyPlayedHandler {

    private final WebClient client;

    public RecentlyPlayedHandler(WebClient oauthWebClient) {
        this.client = oauthWebClient;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        var queryParams = serverRequest.queryParams();
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/me/player/recently-played").queryParams(queryParams).build())
                .retrieve()
                .bodyToMono(ItemList.ofType(Track.class))
                .flatMap(tracklist -> ServerResponse.ok().bodyValue(tracklist));
    }
}
