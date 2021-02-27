package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiscoverHandler {

    private final DiscoverService discoverService;

    public Mono<ServerResponse> discoverTracks(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverTracks(source)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverAlbums(source)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverArtists(source)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
