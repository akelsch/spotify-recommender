package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.album.AlbumIdAndScore;
import de.htwsaar.spotifyrecommender.discover.artist.ArtistIdAndScore;
import de.htwsaar.spotifyrecommender.discover.track.TrackIdAndScore;
import de.htwsaar.spotifyrecommender.spotify.SpotifyApi;
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

    public Flux<TrackIdAndScore> discoverTracks(DiscoverSource source) {
        return getUrisFromSource(source).flatMapMany(uris -> template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_tracks(:uris)")
                .bind("uris", uris)
                .map(row -> new TrackIdAndScore(row.get("track_uri", String.class), row.get("score", Double.class)))
                .all());
    }

    public Flux<AlbumIdAndScore> discoverAlbums(DiscoverSource source) {
        return getUrisFromSource(source).flatMapMany(uris -> template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_albums(:uris)")
                .bind("uris", uris)
                .map(row -> new AlbumIdAndScore(row.get("album_uri", String.class), row.get("score", Double.class)))
                .all());
    }

    public Flux<ArtistIdAndScore> discoverArtists(DiscoverSource source) {
        return getUrisFromSource(source).flatMapMany(uris -> template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_artists(:uris)")
                .bind("uris", uris)
                .map(row -> new ArtistIdAndScore(row.get("artist_uri", String.class), row.get("score", Double.class)))
                .all());
    }

    private Mono<List<String>> getUrisFromSource(DiscoverSource source) {
        return switch (source) {
            case top -> spotifyApi.getTopTracks();
            case recent -> Mono.empty();
            case saved -> Mono.empty();
            case example -> Mono.just(DiscoverExamples.ROCK_PLAYLIST);
        };
    }
}
