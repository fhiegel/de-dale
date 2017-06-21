package com.dedale.cards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class FindCardsByIdsQueryHandler implements QueryHandler<List<Card>, FindCardsByIdsQuery> {
    
    private Map<FindCardsByIdsQuery, List<Card>> cardsByIds = new HashMap<>();
    
    @Inject
    private CardRepository repository;
    
    @Override
    public List<Card> handle(FindCardsByIdsQuery query) {
        return getFromCache(query, cardsByIds, this::getCardList);
    }
    
    private static <R, Q extends Query> R getFromCache(Q query, Map<Q, R> cache, Function<Q, R> cacheFiller) {
        if (cache.containsKey(query)) {
            return cache.get(query);
        }
        R result = cacheFiller.apply(query);
        cache.put(query, result);
        return result;
    }
    
    private List<Card> getCardList(FindCardsByIdsQuery query) {
        return query.getCardIds().stream().map(this::getCardById).collect(Collectors.toList());
    }
    
    private Card getCardById(Long cardId) {
        return repository.getById(cardId);
    }
    
}
