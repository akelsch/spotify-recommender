package de.htwsaar.spotifyrecommender.dataset.projections;

import org.springframework.beans.factory.annotation.Value;

public interface AlbumIdOnly {

    // TODO create utils method for this, used multiple times across the application
    @Value("#{T(org.apache.commons.lang3.StringUtils).substringAfterLast(target.albumUri, ':')}")
    String getId();
}
