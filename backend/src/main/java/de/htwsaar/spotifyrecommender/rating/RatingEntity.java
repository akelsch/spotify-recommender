package de.htwsaar.spotifyrecommender.rating;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("Ratings")
class RatingEntity {

    @Id
    private Long id;

    private String userId;

    private String uri;

    private RatingEntity type;

    private float rate;
}
