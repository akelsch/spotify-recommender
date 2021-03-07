package de.htwsaar.spotifyrecommender.configuration.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpConversation;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.io.ByteBufferAccumulator;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.io.IOException;
import java.net.URI;

/**
 * {@link HttpClient} that logs request and response including body.
 * Useful for testing purposes and the "dev" profile.
 */
@Slf4j
public class LoggingHttpClient extends HttpClient {

    private final ObjectMapper objectMapper;

    public LoggingHttpClient() {
        super(new SslContextFactory.Client());
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected HttpRequest newHttpRequest(HttpConversation conversation, URI uri) {
        HttpRequest httpRequest = super.newHttpRequest(conversation, uri);

        ByteBufferAccumulator acc = new ByteBufferAccumulator();
        httpRequest.onRequestBegin(this::logRequest);
        httpRequest.onResponseContent((response, content) -> acc.copyBuffer(content));
        httpRequest.onResponseSuccess(response -> logResponse(response, acc.toByteArray()));
        httpRequest.onComplete(result -> acc.close());

        return httpRequest;
    }

    private void logRequest(Request request) {
        log.debug("REQ: {} {}", request.getMethod(), request.getURI());
    }

    private void logResponse(Response response, byte[] bytes) {
        String body;
        try {
            body = objectMapper.readValue(bytes, JsonNode.class).toString();
        } catch (IOException e) {
            body = new String(bytes);
        }

        log.debug("RES: {} {}\n{}", response.getStatus(), response.getReason(), body);
    }
}
