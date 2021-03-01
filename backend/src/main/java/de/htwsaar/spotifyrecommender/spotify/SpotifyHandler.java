package de.htwsaar.spotifyrecommender.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class SpotifyHandler {

    private final WebClient webClient;

    @Autowired
    public SpotifyHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ServerResponse> delegate(ServerRequest request) {
        HttpMethod method = Optional.ofNullable(request.method()).orElse(HttpMethod.GET);
        String endpoint = request.requestPath().subPath(2).value();
        MultiValueMap<String, String> queryParams = request.queryParams();

        return webClient.method(method)
                .uri(uriBuilder -> uriBuilder.path(endpoint).queryParams(queryParams).build())
                .exchangeToMono(response -> response.bodyToMono(String.class)
                        .flatMap(body -> ServerResponse.status(response.statusCode())
                                .headers(httpHeaders -> httpHeaders.addAll(response.headers().asHttpHeaders()))
                                .bodyValue(body)));
    }
}
