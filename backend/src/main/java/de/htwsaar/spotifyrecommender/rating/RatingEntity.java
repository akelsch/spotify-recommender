package de.htwsaar.spotifyrecommender.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Table("Ratings")
class RatingEntity {

    @Id
    private Long id;

    private String userId;

    private String uri;

    private RatingType type;

    private float rating;
}
