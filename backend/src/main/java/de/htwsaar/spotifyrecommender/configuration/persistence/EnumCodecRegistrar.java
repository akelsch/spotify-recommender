package de.htwsaar.spotifyrecommender.configuration.persistence;

import de.htwsaar.spotifyrecommender.rating.model.SpotifyType;
import io.netty.buffer.ByteBufAllocator;
import io.r2dbc.postgresql.api.PostgresqlConnection;
import io.r2dbc.postgresql.codec.CodecRegistry;
import io.r2dbc.postgresql.codec.EnumCodec;
import io.r2dbc.postgresql.extension.CodecRegistrar;
import org.reactivestreams.Publisher;

public class EnumCodecRegistrar implements CodecRegistrar {

    private final CodecRegistrar delegate;

    public EnumCodecRegistrar() {
        this.delegate = EnumCodec.builder().withEnum("spotify_type", SpotifyType.class).build();
    }

    @Override
    public Publisher<Void> register(PostgresqlConnection connection, ByteBufAllocator allocator, CodecRegistry registry) {
        return delegate.register(connection, allocator, registry);
    }
}
