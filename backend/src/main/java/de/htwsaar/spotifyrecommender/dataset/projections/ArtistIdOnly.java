package de.htwsaar.spotifyrecommender.dataset.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ArtistIdOnly {

    @Value("#{T(de.htwsaar.spotifyrecommender.util.SpotifyUtils).extractIdFromUri(target.artistUri)}")
    String getId();
}
