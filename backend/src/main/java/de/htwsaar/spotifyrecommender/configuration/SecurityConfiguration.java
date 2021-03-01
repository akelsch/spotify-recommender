package de.htwsaar.spotifyrecommender.configuration;

import de.htwsaar.spotifyrecommender.configuration.security.LoggingHttpClient;
import de.htwsaar.spotifyrecommender.configuration.security.SimpleUrlServerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Value("${de.htwsaar.spotifyrecommender.frontendUrl}")
    private String frontendUrl;

    @Value("${de.htwsaar.spotifyrecommender.redirectUrl}")
    private String redirectUrl;

    private final boolean usingDevProfile;

    public SecurityConfiguration(Environment environment) {
        this.usingDevProfile = environment.acceptsProfiles(Profiles.of("dev"));
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();
        http.cors();

        if (usingDevProfile) {
            http.oauth2Login();
        } else {
            var authenticationSuccessHandler = new SimpleUrlServerAuthenticationSuccessHandler(redirectUrl);
            http.oauth2Login().authenticationSuccessHandler(authenticationSuccessHandler);
        }

        http.authorizeExchange()
                .pathMatchers("/spotify/**", "/api/**").authenticated()
                .anyExchange().permitAll();

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin(frontendUrl);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    WebClient oauthWebClient(ReactiveClientRegistrationRepository clientRegistrationRepository,
                             ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        var oauthFilter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauthFilter.setDefaultOAuth2AuthorizedClient(true);

        WebClient.Builder builder = WebClient.builder();

        if (usingDevProfile) {
            builder.clientConnector(new JettyClientHttpConnector(new LoggingHttpClient()));
        }

        return builder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // 2 MB
                .baseUrl("https://api.spotify.com")
                .filter(oauthFilter)
                .build();
    }

}
