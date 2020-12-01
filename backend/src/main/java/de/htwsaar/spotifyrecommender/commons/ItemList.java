package de.htwsaar.spotifyrecommender.commons;

import lombok.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

import java.util.List;

@Value
public class ItemList<T> {

    List<T> items;

    public static <U> ParameterizedTypeReference<ItemList<U>> ofType(Class<U> type) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ItemList.class, type);
        return ParameterizedTypeReference.forType(resolvableType.getType());
    }
}
