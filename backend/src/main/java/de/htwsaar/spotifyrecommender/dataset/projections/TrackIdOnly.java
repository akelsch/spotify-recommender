package de.htwsaar.spotifyrecommender.dataset.projections;

import org.springframework.beans.factory.annotation.Value;

public interface TrackIdOnly {

    @Value("#{T(de.htwsaar.spotifyrecommender.util.SpotifyUtils).extractIdFromUri(target.trackUri)}")
    String getId();
}
