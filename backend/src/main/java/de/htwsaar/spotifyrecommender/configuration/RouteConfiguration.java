package de.htwsaar.spotifyrecommender.configuration;

import de.htwsaar.spotifyrecommender.discover.DiscoverHandler;
import de.htwsaar.spotifyrecommender.recent.RecentlyPlayedHandler;
import de.htwsaar.spotifyrecommender.spotify.SpotifyHandler;
import de.htwsaar.spotifyrecommender.user.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfiguration {

    @Bean
    RouterFunction<ServerResponse> routeUser(UserHandler userHandler) {
        return route(GET("/me").and(accept(APPLICATION_JSON)), userHandler::me);
    }

    @Bean
    RouterFunction<ServerResponse> routeSpotify(SpotifyHandler spotifyHandler) {
        return route(path("/spotify/**"), spotifyHandler::deligate);
    }

    @Bean
    RouterFunction<ServerResponse> routeRecentlyPlayed(RecentlyPlayedHandler recentlyPlayedHandler) {
        var routes = route(GET(""), recentlyPlayedHandler::get);

        return nest(path("/api/v1/recently-played").and(accept(APPLICATION_JSON)), routes);
    }

    @Bean
    RouterFunction<ServerResponse> routeDiscover(DiscoverHandler discoverHandler) {
        var routes = route(GET("/tracks"), discoverHandler::discoverTracks)
                .and(route(GET("/albums"), discoverHandler::discoverAlbums))
                .and(route(GET("/artists"), discoverHandler::discoverArtists));

        return nest(path("/api/v1/discover").and(accept(APPLICATION_JSON)), routes);
    }
}
