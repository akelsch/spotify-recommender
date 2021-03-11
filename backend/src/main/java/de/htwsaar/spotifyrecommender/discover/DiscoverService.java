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
import de.htwsaar.spotifyrecommender.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DiscoverService {

    private final SpotifyApi spotifyApi;
    private final R2dbcEntityTemplate template;

    @Autowired
    public DiscoverService(SpotifyApi spotifyApi, R2dbcEntityTemplate template) {
        this.spotifyApi = spotifyApi;
        this.template = template;
    }

    public Mono<TracksResponse> discoverTracks(Source source, TimeRange timeRange, boolean filter) {
        return fetchUrisFromSpotify(source, timeRange)
                .flatMapMany(uris -> doJaccardForTracks(uris, filter))
                .map(TrackIdAndScore::getId)
                .collectList()
                .flatMap(spotifyApi::getSeveralTracks);
    }

    public Mono<TracksResponse> discoverTracksWithWeights(Source source, TimeRange timeRange, boolean filter) {
        return fetchUrisFromSpotify(source, timeRange)
                .zipWith(SecurityUtils.getUserId())
                .flatMapMany(tuple -> doJaccardForTracksWithWeights(tuple.getT1(), filter, tuple.getT2()))
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

    public Mono<AlbumsResponse> discoverAlbumsWithWeights(Source source, TimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .zipWith(SecurityUtils.getUserId())
                .flatMapMany(tuple -> doJaccardForAlbumsWithWeights(tuple.getT1(), tuple.getT2()))
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

    public Mono<ArtistsResponse> discoverArtistsWithWeights(Source source, TimeRange timeRange) {
        return fetchUrisFromSpotify(source, timeRange)
                .zipWith(SecurityUtils.getUserId())
                .flatMapMany(tuple -> doJaccardForArtistsWithWeights(tuple.getT1(), tuple.getT2()))
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
                .sql("SELECT * FROM my_jaccard_tracks(:uris, :filter)")
                .bind("uris", uris.toArray(String[]::new))
                .bind("filter", filter)
                .map(row -> new TrackIdAndScore(row.get("track_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<TrackIdAndScore> doJaccardForTracksWithWeights(List<String> uris, boolean filter, String user) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_tracks_weights(:uris, :filter, :user)")
                .bind("uris", uris.toArray(String[]::new))
                .bind("filter", filter)
                .bind("user", user)
                .map(row -> new TrackIdAndScore(row.get("track_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<AlbumIdAndScore> doJaccardForAlbums(List<String> uris) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_albums(:uris)")
                .bind("uris", uris.toArray(String[]::new))
                .map(row -> new AlbumIdAndScore(row.get("album_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<AlbumIdAndScore> doJaccardForAlbumsWithWeights(List<String> uris, String user) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_albums_weights(:uris, :user)")
                .bind("uris", uris.toArray(String[]::new))
                .bind("user", user)
                .map(row -> new AlbumIdAndScore(row.get("album_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<ArtistIdAndScore> doJaccardForArtists(List<String> uris) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_artists(:uris)")
                .bind("uris", uris.toArray(String[]::new))
                .map(row -> new ArtistIdAndScore(row.get("artist_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    private Flux<ArtistIdAndScore> doJaccardForArtistsWithWeights(List<String> uris, String user) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_artists_weights(:uris, :user)")
                .bind("uris", uris.toArray(String[]::new))
                .bind("user", user)
                .map(row -> new ArtistIdAndScore(row.get("artist_uri", String.class), row.get("score", Double.class)))
                .all();
    }
}
