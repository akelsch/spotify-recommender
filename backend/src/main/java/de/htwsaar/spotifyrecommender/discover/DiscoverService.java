package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.param.Source;
import de.htwsaar.spotifyrecommender.discover.param.TimeRange;
import de.htwsaar.spotifyrecommender.discover.projection.AlbumIdAndScore;
import de.htwsaar.spotifyrecommender.discover.projection.ArtistIdAndScore;
import de.htwsaar.spotifyrecommender.discover.projection.TrackIdAndScore;
import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
import de.htwsaar.spotifyrecommender.spotify.model.album.AlbumsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.artist.ArtistsResponse;
import de.htwsaar.spotifyrecommender.spotify.model.track.TracksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<TracksResponse> discoverTracks(Source source, TimeRange timeRange, boolean filter) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(uris -> doJaccardForTracks(uris, filter))
                .map(TrackIdAndScore::getId)
                .collectList()
                .flatMap(spotifyApi::getSeveralTracks);
    }

    public Mono<AlbumsResponse> discoverAlbums(Source source, TimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(this::doJaccardForAlbums)
                .map(AlbumIdAndScore::getId)
                .collectList()
                .flatMap(spotifyApi::getSeveralAlbums);
    }

    public Mono<ArtistsResponse> discoverArtists(Source source, TimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(this::doJaccardForArtists)
                .map(ArtistIdAndScore::getId)
                .collectList()
                .flatMap(spotifyApi::getSeveralArtists);
    }

    private Mono<List<String>> fetchUrisFromSpotify(Source source, TimeRange timeRange) {
        return switch (source) {
            case top -> spotifyApi.getTopTracks(50, timeRange.toString());
            case recent -> spotifyApi.getRecentlyPlayedTracks(50);
            case saved -> spotifyApi.getSavedTracks(50);
            case example -> Mono.just(Source.EXAMPLE_PLAYLIST);
        };
    }

    private Flux<TrackIdAndScore> doJaccardForTracks(List<String> uris, boolean filter) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_tracks(:filter, :uris)")
                .bind("filter", filter)
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
