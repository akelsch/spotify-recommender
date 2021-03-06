package de.htwsaar.spotifyrecommender.configuration.persistence;

import de.htwsaar.spotifyrecommender.rating.model.SpotifyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class SpotifyTypeWritingConverter implements Converter<SpotifyType, SpotifyType> {

    @Override
    public SpotifyType convert(SpotifyType source) {
        return source;
    }
}
