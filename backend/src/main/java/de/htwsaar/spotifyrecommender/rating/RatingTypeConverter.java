package de.htwsaar.spotifyrecommender.rating;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class RatingTypeConverter implements Converter<String, RatingType> {

    @Override
    public RatingType convert(String source) {
        return RatingType.valueOf(source);
    }
}
