package de.htwsaar.spotifyrecommender.spotify;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class SpotifyHandler {

    private final WebClient client;

    public SpotifyHandler(WebClient oauthWebClient) {
        this.client = oauthWebClient;
    }

    public Mono<ServerResponse> deligate(ServerRequest serverRequest) {
        HttpMethod method = Optional.ofNullable(serverRequest.method()).orElse(HttpMethod.GET);
        String endpoint = serverRequest.requestPath().subPath(2).value();
        var queryParams = serverRequest.queryParams();

        return client.method(method)
                .uri(uriBuilder -> uriBuilder.path(endpoint).queryParams(queryParams).build())
                .exchangeToMono(response -> response.bodyToMono(String.class)
                        .flatMap(body -> ServerResponse.status(response.statusCode())
                                .headers(httpHeaders -> httpHeaders.addAll(response.headers().asHttpHeaders()))
                                .bodyValue(body)));
    }
}
