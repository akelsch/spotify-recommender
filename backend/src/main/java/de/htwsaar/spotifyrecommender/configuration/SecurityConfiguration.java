package de.htwsaar.spotifyrecommender.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();
        http.oauth2Login();

        http.authorizeExchange()
                .pathMatchers("/spotify/**").authenticated()
                .anyExchange().permitAll();

        return http.build();
    }

    @Bean
    ServerOAuth2AuthorizedClientExchangeFilterFunction oauthFilter(ReactiveClientRegistrationRepository clientRegistrationRepository,
                                                                   ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        var oauthFilter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauthFilter.setDefaultOAuth2AuthorizedClient(true);
        return oauthFilter;
    }

}
