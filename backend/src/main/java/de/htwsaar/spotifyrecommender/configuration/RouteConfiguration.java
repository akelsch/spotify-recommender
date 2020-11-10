package de.htwsaar.spotifyrecommender.configuration;

import de.htwsaar.spotifyrecommender.recent.RecentlyPlayedHandler;
import de.htwsaar.spotifyrecommender.song.SongHandler;
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
        var routes = route(GET("/recently-played").and(accept(APPLICATION_JSON)), recentlyPlayedHandler::get);
        return nest(path("/api/v1/"), routes);
    }

    @Bean
    RouterFunction<ServerResponse> routeSongs(SongHandler songHandler) {
        var routes = route(GET("/").and(accept(APPLICATION_JSON)), songHandler::query)
                .and(route(POST("/").and(accept(APPLICATION_JSON)), songHandler::create))
                .and(route(GET("/{id}").and(accept(APPLICATION_JSON)), songHandler::read))
                .and(route(PUT("/{id}").and(accept(APPLICATION_JSON)), songHandler::update))
                .and(route(DELETE("/{id}").and(accept(APPLICATION_JSON)), songHandler::delete));

        return nest(path("/api/v1/songs"), routes);
    }
}
