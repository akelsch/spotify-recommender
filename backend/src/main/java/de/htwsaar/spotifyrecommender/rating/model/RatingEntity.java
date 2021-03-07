package de.htwsaar.spotifyrecommender.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * POJO that represents the "ratings" database table.
 */
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Table("ratings")
public class RatingEntity {

    @Id
    private Long id;

    private String userId;

    private String uri;

    private SpotifyType type;

    private float rating;
}
