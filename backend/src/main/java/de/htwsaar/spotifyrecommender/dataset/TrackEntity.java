package de.htwsaar.spotifyrecommender.dataset;

import lombok.Data;

/**
 * POJO that represents the Tracks database table.
 */
@Data
public class TrackEntity {

    private Long id;

    private int pidFk;

    private String trackUri;

    private String artistUri;

    private String albumUri;

    private int pos;
}
