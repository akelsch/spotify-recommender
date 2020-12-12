package de.htwsaar.spotifyrecommender.rating;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Ratings")
class RatingEntity {

    @Id
    private Long id;

    private String userId;

    private String uri;

    private RatingType type;

    private float rating;
}
