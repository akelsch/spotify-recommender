package de.htwsaar.spotifyrecommender.configuration.persistence;

import de.htwsaar.spotifyrecommender.rating.model.SpotifyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Provides Postgres write support for the {@link SpotifyType} enum
 * because the driver otherwise assumes it to be of type character varying.
 */
@WritingConverter
public class SpotifyTypeWritingConverter implements Converter<SpotifyType, SpotifyType> {

    @Override
    public SpotifyType convert(SpotifyType source) {
        return source;
    }
}
