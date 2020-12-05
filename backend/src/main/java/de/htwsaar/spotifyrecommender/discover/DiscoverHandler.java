package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.dataset.TrackEntityService;
import de.htwsaar.spotifyrecommender.dataset.projections.TrackIdOnly;
import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiscoverHandler {

    private final TrackEntityService trackEntityService;
    private final SpotifyApi spotifyApi;

    public Mono<ServerResponse> discoverTracks(ServerRequest serverRequest) {
        int page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(0);
        int size = serverRequest.queryParam("size").map(Integer::parseInt).orElse(20);

        return trackEntityService.findAllTrackIds(PageRequest.of(page, size))
                .map(TrackIdOnly::getId)
                .collect(Collectors.toList())
                .flatMap(ids -> spotifyApi.getSeveralTracks(ids).bodyToMono(DiscoverResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest serverRequest) {
        return null;
    }
}
