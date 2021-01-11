package de.htwsaar.spotifyrecommender.dataset;

import de.htwsaar.spotifyrecommender.dataset.projections.AlbumIdOnly;
import de.htwsaar.spotifyrecommender.dataset.projections.ArtistIdOnly;
import de.htwsaar.spotifyrecommender.dataset.projections.TrackIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TrackEntityService {

    private final R2dbcEntityTemplate template;

    @Autowired
    public TrackEntityService(R2dbcEntityTemplate template) {
        this.template = template;
    }

    public Flux<TrackIdOnly> findAllTrackIds(Pageable pageable) {
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        return template.select(TrackEntity.class).as(TrackIdOnly.class)
                .matching(Query.empty().columns("track_uri").offset(offset).limit(limit))
                .all();
    }

    public Flux<AlbumIdOnly> findAllAlbumIds(Pageable pageable) {
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        return template.select(TrackEntity.class).as(AlbumIdOnly.class)
                .matching(Query.empty().columns("album_uri").offset(offset).limit(limit))
                .all();
    }

    public Flux<ArtistIdOnly> findAllArtistIds(Pageable pageable) {
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        return template.select(TrackEntity.class).as(ArtistIdOnly.class)
                .matching(Query.empty().columns("artist_uri").offset(offset).limit(limit))
                .all();
    }
}
