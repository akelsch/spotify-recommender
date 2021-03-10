package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.param.Source;
import de.htwsaar.spotifyrecommender.discover.param.TimeRange;
import de.htwsaar.spotifyrecommender.util.RequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiscoverHandler {

    private final DiscoverService discoverService;
    private final RequestConverter requestConverter;

    public Mono<ServerResponse> discoverTracks(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);
        var filter = requestConverter.queryParam(request, "filter", Boolean.class).orElse(false);

        return discoverService.discoverTracks(source, timeRange, filter)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverTracksWithWeights(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);
        var filter = requestConverter.queryParam(request, "filter", Boolean.class).orElse(false);

        return discoverService.discoverTracksWithWeights(source, timeRange, filter)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);

        return discoverService.discoverAlbums(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbumsWithWeights(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);

        return discoverService.discoverAlbumsWithWeights(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);

        return discoverService.discoverArtists(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtistsWithWeights(ServerRequest request) {
        var source = requestConverter.requiredQueryParam(request, "source", Source.class);
        var timeRange = requestConverter.queryParam(request, "time_range", TimeRange.class)
                .orElse(TimeRange.medium_term);

        return discoverService.discoverArtistsWithWeights(source, timeRange)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
