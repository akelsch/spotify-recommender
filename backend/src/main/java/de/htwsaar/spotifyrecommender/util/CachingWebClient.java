package de.htwsaar.spotifyrecommender.util;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;

@Component
public class CachingWebClient {

    private static final String CACHE_KEY_SEPARATOR = "::";

    private final WebClient webClient;
    private final Cache<String, Object> cache;

    @Autowired
    public CachingWebClient(WebClient webClient, Cache<String, Object> cache) {
        this.webClient = webClient;
        this.cache = cache;
    }

    public <T> Mono<T> doGet(String uri, Class<T> clazz) {
        return doRequest(HttpMethod.GET, uri, clazz);
    }

    public <T> Mono<T> doRequest(HttpMethod method, String uri, Class<T> clazz) {
        return SecurityUtils.getUserId()
                .flatMap(userId -> {
                    String key = userId + CACHE_KEY_SEPARATOR + uri;
                    return CacheMono.<String, T>lookup(cache.asMap(), key)
                            .onCacheMissResume(() -> delegate(method, uri, clazz));
                });
    }

    private <T> Mono<T> delegate(HttpMethod method, String uri, Class<T> clazz) {
        return webClient.method(method)
                .uri(uri)
                .retrieve()
                .bodyToMono(clazz);
    }
}
