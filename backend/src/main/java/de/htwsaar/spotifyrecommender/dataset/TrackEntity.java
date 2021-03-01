package de.htwsaar.spotifyrecommender.dataset;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * POJO that represents the "tracks" database table.
 */
@Data
@Table("tracks")
class TrackEntity {

    @Id
    private Long id;

    @Column("pid_fk")
    private int pid;

    private String trackUri;

    private String artistUri;

    private String albumUri;

    private int pos;
}
