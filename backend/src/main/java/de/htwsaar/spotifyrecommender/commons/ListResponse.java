package de.htwsaar.spotifyrecommender.commons;

import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

import java.util.List;

@Data
public class ListResponse<T> {

    private List<T> items;

    public static <U> ParameterizedTypeReference<ListResponse<U>> ofType(Class<U> type) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ListResponse.class, type);
        return ParameterizedTypeReference.forType(resolvableType.getType());
    }
}
