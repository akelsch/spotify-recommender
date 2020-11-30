package de.htwsaar.spotifyrecommender.dataset;

import lombok.Data;

/**
 * POJO that represents the Playlists database table.
 */
@Data
public class PlaylistEntity {

    private Integer pid;

    private long modifiedAt;

    private int numTracks;

    private int numAlbums;

    private int numFollowers;
}
