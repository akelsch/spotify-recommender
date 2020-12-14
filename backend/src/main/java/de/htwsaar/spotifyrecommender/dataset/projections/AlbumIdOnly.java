package de.htwsaar.spotifyrecommender.dataset.projections;

import org.springframework.beans.factory.annotation.Value;

public interface AlbumIdOnly {

    @Value("#{T(de.htwsaar.spotifyrecommender.util.SpotifyUtils).extractIdFromUri(target.albumUri)}")
    String getId();
}
