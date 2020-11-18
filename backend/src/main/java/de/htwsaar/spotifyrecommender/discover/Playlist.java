package de.htwsaar.spotifyrecommender.discover;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("playlists")
public class Playlist {

    @Id
    private Integer pid;

    private long modifiedAt;

    private int numTracks;

    private int numAlbums;

    private int numFollowers;
}
