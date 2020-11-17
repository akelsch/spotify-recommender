package de.htwsaar.spotifyrecommender.discover;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("playlists")
public class Playlist {

    @Id
    private Integer pid;

    private long modified_at;

    private int num_tracks;

    private int num_albums;

    private int num_followers;
}
