package de.htwsaar.spotifyrecommender.util;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import reactor.core.publisher.Mono;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Mono<String> getUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .cast(OAuth2AuthenticationToken.class)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .flatMap(user -> Mono.justOrEmpty(user.<String>getAttribute("id")))
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Missing attribute 'id' in attributes")));
    }
}
