package de.htwsaar.spotifyrecommender.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Value("${de.htwsaar.spotifyrecommender.redirectUrl}")
    private String redirectUrl;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();

        var authenticationSuccessHandler = new SimpleUrlServerAuthenticationSuccessHandler(redirectUrl);
        http.oauth2Login();

        http.authorizeExchange()
                .pathMatchers("/spotify/**", "/api/v1/**").authenticated()
                .anyExchange().permitAll();

        return http.build();
    }

    @Bean
    WebClient oauthWebClient(ReactiveClientRegistrationRepository clientRegistrationRepository,
                             ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        var oauthFilter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauthFilter.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .baseUrl("https://api.spotify.com")
                .filter(oauthFilter)
                .build();
    }

}
