package de.htwsaar.spotifyrecommender.discover;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DiscoverHandler {

    public Mono<ServerResponse> discoverTracks(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest serverRequest) {
        return null;
    }
}
