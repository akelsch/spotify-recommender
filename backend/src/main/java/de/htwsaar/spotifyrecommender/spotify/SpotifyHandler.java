package de.htwsaar.spotifyrecommender.spotify;

import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SpotifyHandler {

    private final WebClient client;

    public SpotifyHandler(ServerOAuth2AuthorizedClientExchangeFilterFunction oauthFilter) {
        this.client = WebClient.builder()
                .baseUrl("https://api.spotify.com")
                .filter(oauthFilter)
                .build();
    }

    public Mono<ServerResponse> deligate(ServerRequest serverRequest) {
        HttpMethod method = serverRequest.method();
        String endpoint = serverRequest.pathContainer().subPath(2).value();
        var queryParams = serverRequest.queryParams();

        return client.method(method)
                .uri(uriBuilder -> uriBuilder.path(endpoint).queryParams(queryParams).build())
                .exchange()
                .flatMap(response -> ServerResponse.status(response.statusCode())
                        .headers(httpHeaders -> httpHeaders.addAll(response.headers().asHttpHeaders()))
                        .body(response.bodyToMono(String.class), String.class));
    }
}