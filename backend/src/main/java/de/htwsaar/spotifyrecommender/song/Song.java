package de.htwsaar.spotifyrecommender.song;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("songs")
public class Song {

    @Id
    private String id;

    private String name;

    private String imageUri;
}
