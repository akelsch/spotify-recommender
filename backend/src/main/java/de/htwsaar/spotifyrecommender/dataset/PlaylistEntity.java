package de.htwsaar.spotifyrecommender.dataset;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * POJO that represents the "playlists" database table.
 */
@Data
@Table("playlists")
public class PlaylistEntity {

    @Id
    private Integer pid;

    private long modifiedAt;

    private int numTracks;

    private int numAlbums;

    private int numFollowers;
}
