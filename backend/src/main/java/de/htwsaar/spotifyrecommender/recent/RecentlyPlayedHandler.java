package de.htwsaar.spotifyrecommender.recent;

import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RecentlyPlayedHandler {

    private final SpotifyApi spotifyApi;

    public Mono<ServerResponse> get(ServerRequest request) {
        MultiValueMap<String, String> queryParams = request.queryParams();
        return spotifyApi.getRecentlyPlayed(queryParams)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
