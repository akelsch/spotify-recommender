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
        var source = RequestUtils.requiredEnumQueryParam(request, "source", DiscoverSource.class);
        var timeRange = RequestUtils.enumQueryParam(request, "time_range", DiscoverTimeRange.class)
                .orElse(DiscoverTimeRange.medium_term);

        return discoverService.discoverTracks(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest request) {
        var source = RequestUtils.requiredEnumQueryParam(request, "source", DiscoverSource.class);
        var timeRange = RequestUtils.enumQueryParam(request, "time_range", DiscoverTimeRange.class)
                .orElse(DiscoverTimeRange.medium_term);

        return discoverService.discoverAlbums(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest request) {
        var source = RequestUtils.requiredEnumQueryParam(request, "source", DiscoverSource.class);
        var timeRange = RequestUtils.enumQueryParam(request, "time_range", DiscoverTimeRange.class)
                .orElse(DiscoverTimeRange.medium_term);

        return discoverService.discoverArtists(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
