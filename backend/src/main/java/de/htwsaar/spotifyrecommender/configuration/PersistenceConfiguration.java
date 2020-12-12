package de.htwsaar.spotifyrecommender.configuration;

import de.htwsaar.spotifyrecommender.rating.RatingTypeWritingConverter;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.util.List;

@Configuration
public class PersistenceConfiguration extends AbstractR2dbcConfiguration {

    @Bean
    @Profile("default")
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new RatingTypeWritingConverter());
    }

}
