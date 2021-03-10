package de.htwsaar.spotifyrecommender.util.web;

import com.github.benmanes.caffeine.cache.Cache;
import de.htwsaar.spotifyrecommender.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;

/**
 * {@link WebClient} adapter that caches requests.
 */
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

    /**
     * Does a HTTP GET request for the given URI expecting a body of the provided type.
     *
     * @param uri   the URI for the request
     * @param clazz the target type of the response body
     * @param <T>   the response type
     * @return the parsed response body wrapped in a {@link Mono}
     */
    public <T> Mono<T> doGet(String uri, Class<T> clazz) {
        return doRequest(HttpMethod.GET, uri, clazz);
    }

    /**
     * Does an arbitrary HTTP request for the given URI expecting a body of the provided type.
     *
     * @param method the HTTP method of the request
     * @param uri    the URI for the request
     * @param clazz  the target type of the response body
     * @param <T>    the response type
     * @return the parsed response body wrapped in a {@link Mono}
     */
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
