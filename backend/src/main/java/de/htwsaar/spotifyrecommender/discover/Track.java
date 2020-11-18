package de.htwsaar.spotifyrecommender.discover;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tracks")
public class Track {

    @Id
    private Long id;

    @Column("pid_fk")
    private int pid;

    private String trackUri;

    private String artistUri;

    private String albumUri;

    private int pos;
}
