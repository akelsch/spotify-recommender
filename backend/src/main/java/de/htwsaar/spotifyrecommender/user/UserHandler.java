package de.htwsaar.spotifyrecommender.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    public Mono<ServerResponse> me(ServerRequest request) {
        return request.principal()
                .cast(OAuth2AuthenticationToken.class)
                .flatMap(token -> ServerResponse.ok().bodyValue(token.getPrincipal().getAttributes()))
                .switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).build());
    }
}
