package de.htwsaar.spotifyrecommender.rating;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class RatingTypeWritingConverter implements Converter<RatingType, RatingType> {

    @Override
    public RatingType convert(RatingType source) {
        return source;
    }
}
