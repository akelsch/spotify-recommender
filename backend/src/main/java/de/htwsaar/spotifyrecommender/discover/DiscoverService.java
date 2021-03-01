package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.album.AlbumIdAndScore;
import de.htwsaar.spotifyrecommender.discover.album.DiscoverAlbumResponse;
import de.htwsaar.spotifyrecommender.discover.artist.ArtistIdAndScore;
import de.htwsaar.spotifyrecommender.discover.artist.DiscoverArtistResponse;
import de.htwsaar.spotifyrecommender.discover.track.DiscoverTrackResponse;
import de.htwsaar.spotifyrecommender.discover.track.TrackIdAndScore;
import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class DiscoverService {

    private final R2dbcEntityTemplate template;
    private final SpotifyApi spotifyApi;

    @Autowired
    public DiscoverService(R2dbcEntityTemplate template, SpotifyApi spotifyApi) {
        this.template = template;
        this.spotifyApi = spotifyApi;
    }

    public Mono<DiscoverTrackResponse> discoverTracks(DiscoverSource source, DiscoverTimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(this::doJaccardForTracks)
                .map(TrackIdAndScore::getId)
                .collectList()
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> spotifyApi.getSeveralTracks(ids).bodyToMono(DiscoverTrackResponse.class))
                .defaultIfEmpty(new DiscoverTrackResponse(Collections.emptyList()));
    }

    public Mono<DiscoverAlbumResponse> discoverAlbums(DiscoverSource source, DiscoverTimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(this::doJaccardForAlbums)
                .map(AlbumIdAndScore::getId)
                .collectList()
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> spotifyApi.getSeveralAlbums(ids).bodyToMono(DiscoverAlbumResponse.class))
                .defaultIfEmpty(new DiscoverAlbumResponse(Collections.emptyList()));
    }

    public Mono<DiscoverArtistResponse> discoverArtists(DiscoverSource source, DiscoverTimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(this::doJaccardForArtists)
                .map(ArtistIdAndScore::getId)
                .collectList()
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> spotifyApi.getSeveralArtists(ids).bodyToMono(DiscoverArtistResponse.class))
                .defaultIfEmpty(new DiscoverArtistResponse(Collections.emptyList()));
    }

    private Mono<List<String>> fetchUrisFromSpotify(DiscoverSource source, DiscoverTimeRange timeRange) {
        return switch (source) {
            case top -> spotifyApi.getTopTracks(timeRange.toString());
            case recent -> spotifyApi.getRecentlyPlayedTracks();
            case saved -> spotifyApi.getSavedTracks();
            case example -> Mono.just(DiscoverSource.EXAMPLE_PLAYLIST);
        };
    }

    private Flux<TrackIdAndScore> doJaccardForTracks(List<String> uris) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_tracks(:uris)")
                .bind("uris", uris)
                .map(row -> new TrackIdAndScore(row.get("track_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<AlbumIdAndScore> doJaccardForAlbums(List<String> uris) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_albums(:uris)")
                .bind("uris", uris)
                .map(row -> new AlbumIdAndScore(row.get("album_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<ArtistIdAndScore> doJaccardForArtists(List<String> uris) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_artists(:uris)")
                .bind("uris", uris)
                .map(row -> new ArtistIdAndScore(row.get("artist_uri", String.class), row.get("score", Double.class)))
                .all();
    }
}
