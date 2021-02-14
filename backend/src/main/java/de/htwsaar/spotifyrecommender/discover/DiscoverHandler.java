package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.dataset.TrackEntityService;
import de.htwsaar.spotifyrecommender.dataset.projections.AlbumIdOnly;
import de.htwsaar.spotifyrecommender.dataset.projections.ArtistIdOnly;
import de.htwsaar.spotifyrecommender.dataset.projections.TrackIdOnly;
import de.htwsaar.spotifyrecommender.discover.album.DiscoverAlbumResponse;
import de.htwsaar.spotifyrecommender.discover.artist.DiscoverArtistResponse;
import de.htwsaar.spotifyrecommender.discover.track.DiscoverTrackResponse;
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
                .flatMap(ids -> spotifyApi.getSeveralTracks(ids).bodyToMono(DiscoverTrackResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest serverRequest) {
        int page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(0);
        int size = serverRequest.queryParam("size").map(Integer::parseInt).orElse(20);

        return trackEntityService.findAllAlbumIds((PageRequest.of(page, size)))
                .map(AlbumIdOnly::getId)
                .collect(Collectors.toList())
                .flatMap(ids -> spotifyApi.getSeveralAlbums(ids).bodyToMono(DiscoverAlbumResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest serverRequest) {
        int page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(0);
        int size = serverRequest.queryParam("size").map(Integer::parseInt).orElse(20);

        return trackEntityService.findAllArtistIds(PageRequest.of(page, size))
                .map(ArtistIdOnly::getId)
                .collect(Collectors.toList())
                .flatMap(ids -> spotifyApi.getSeveralArtists(ids).bodyToMono(DiscoverArtistResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
