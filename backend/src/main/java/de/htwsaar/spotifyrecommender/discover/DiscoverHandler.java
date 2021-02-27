package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.album.AlbumIdAndScore;
import de.htwsaar.spotifyrecommender.discover.album.DiscoverAlbumResponse;
import de.htwsaar.spotifyrecommender.discover.artist.ArtistIdAndScore;
import de.htwsaar.spotifyrecommender.discover.artist.DiscoverArtistResponse;
import de.htwsaar.spotifyrecommender.discover.track.DiscoverTrackResponse;
import de.htwsaar.spotifyrecommender.discover.track.TrackIdAndScore;
import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
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
    private final SpotifyApi spotifyApi;

    public Mono<ServerResponse> discoverTracks(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverTracks(source)
                .map(TrackIdAndScore::getId)
                .collectList()
                .flatMap(ids -> spotifyApi.getSeveralTracks(ids).bodyToMono(DiscoverTrackResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverAlbums(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverAlbums(source)
                .map(AlbumIdAndScore::getId)
                .collectList()
                .flatMap(ids -> spotifyApi.getSeveralAlbums(ids).bodyToMono(DiscoverAlbumResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> discoverArtists(ServerRequest request) {
        DiscoverSource source = RequestUtils.getEnumQueryParam(request, "source", DiscoverSource.class);
        return discoverService.discoverArtists(source)
                .map(ArtistIdAndScore::getId)
                .collectList()
                .flatMap(ids -> spotifyApi.getSeveralArtists(ids).bodyToMono(DiscoverArtistResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
