package com.dedale.cards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindCardsByIdsQueryHandler implements QueryHandler<CardContainer, FindCardsByIdsQuery> {
    
    private Map<FindCardsByIdsQuery, CardContainer> cardsByIds = new HashMap<>();
    
    @Override
    public CardContainer handle(FindCardsByIdsQuery query) {
        return getFormCache(query, cardsByIds, this::getContainer);
    }
    
    private static <R, Q extends Query> R getFormCache(Q query, Map<Q, R> cache, Function<Q, R> cacheFiller) {
        if (cache.containsKey(query)) {
            return cache.get(query);
        }
        
        R container = cacheFiller.apply(query);
        cache.put(query, container);
        return container;
    }
    
    private CardContainer getContainer(FindCardsByIdsQuery query) {
        List<Card> cardList = query.getCardIds().stream().map(this::getCardById).collect(Collectors.toList());
        return new CardContainer(cardList);
    }
    
    private Card getCardById(String cardId) {
        return new Card(cardId);
    }
    
}
