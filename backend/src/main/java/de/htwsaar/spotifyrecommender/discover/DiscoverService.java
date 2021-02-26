package de.htwsaar.spotifyrecommender.discover;

import de.htwsaar.spotifyrecommender.discover.album.AlbumIdAndScore;
import de.htwsaar.spotifyrecommender.discover.artist.ArtistIdAndScore;
import de.htwsaar.spotifyrecommender.discover.track.TrackIdAndScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class DiscoverService {

    // TODO make this list an method parameter
    private static final List<String> EXAMPLE_PLAYLIST = List.of(
            "spotify:track:3FUS56gKr9mVBmzvlnodlh",
            "spotify:track:2MuWTIM3b0YEAskbeeFE1i",
            "spotify:track:4nRyBgsqXEP2oPfzaMeZr7",
            "spotify:track:32dnKMni3I3gwUbWp4mi45",
            "spotify:track:3QZ7uX97s82HFYSmQUAN1D",
            "spotify:track:63wqgs60gUQgdYnkTjYtby",
            "spotify:track:5DiXcVovI0FcY2s0icWWUu",
            "spotify:track:5hE3T2329sdMmIrHxjhQDL",
            "spotify:track:7txxAtOMwLLnQTpKeBL6bp",
            "spotify:track:6hTcuIQa0sxrrByu9wTD7s",
            "spotify:track:2u2Mdn5MXtq8EaDZkG8olK",
            "spotify:track:3pPyv196sOJrbZdNiVNYCA",
            "spotify:track:0qjXqT1EDK5WWYi7w1yIFk",
            "spotify:track:7bG5NDhlrDel1aNofKPNf6",
            "spotify:track:4dVbhS6OiYvFikshyaQaCN",
            "spotify:track:2SbsvihBJmgNKZ4qkXFWrp",
            "spotify:track:3VPp3qrfCioIbPmp7c7Con",
            "spotify:track:4T1PtJLCbe7MIsvhQZURXL",
            "spotify:track:5CPXR6lDTvngxtmMZxnWmC",
            "spotify:track:6OSyCAmXT4Gkd3OQ2aPOaF",
            "spotify:track:618hiI74zBL8UVgAvfmkLj",
            "spotify:track:7GRq9NUgqhxPGUUksX02fk",
            "spotify:track:35SiNV5YHBDsuiTtD0CIwc",
            "spotify:track:21HWc9gFZKNdkGzGqHLq3A",
            "spotify:track:2LWFw1lxYO3uX3T1kGhJpp");

    private final R2dbcEntityTemplate template;

    @Autowired
    public DiscoverService(R2dbcEntityTemplate template) {
        this.template = template;
    }

    public Flux<TrackIdAndScore> discoverTracks() {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_tracks(:uris)")
                .bind("uris", EXAMPLE_PLAYLIST)
                .map(row -> new TrackIdAndScore(row.get("track_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    public Flux<AlbumIdAndScore> discoverAlbums() {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_albums(:uris)")
                .bind("uris", EXAMPLE_PLAYLIST)
                .map(row -> new AlbumIdAndScore(row.get("album_uri", String.class), row.get("score", Double.class)))
                .all();
    }

    public Flux<ArtistIdAndScore> discoverArtists() {
        return template.getDatabaseClient()
                .sql("SELECT * FROM my_jaccard_artists(:uris)")
                .bind("uris", EXAMPLE_PLAYLIST)
                .map(row -> new ArtistIdAndScore(row.get("artist_uri", String.class), row.get("score", Double.class)))
                .all();
    }
}
