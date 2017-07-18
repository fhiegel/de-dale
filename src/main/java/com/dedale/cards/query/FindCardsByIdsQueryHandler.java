package com.dedale.cards.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.dedale.cards.Card;
import com.dedale.cards.CardContainer;
import com.dedale.cards.CardRepository;
import com.dedale.common.Query;
import com.dedale.common.QueryHandler;

@Service
public class FindCardsByIdsQueryHandler implements QueryHandler<CardContainer, FindCardsByIdsQuery> {

    private Map<FindCardsByIdsQuery, CardContainer> cardsByIds = new HashMap<>();

    @Inject
    private CardRepository repository;

    @Override
    public Class<FindCardsByIdsQuery> listenTo() {
        return FindCardsByIdsQuery.class;
    }

    @Override
    public CardContainer handle(FindCardsByIdsQuery query) {
        return getFromCache(query, cardsByIds, this::getCardList);
    }

    private static <R, Q extends Query<R>> R getFromCache(Q query, Map<Q, R> cache, Function<Q, R> cacheFiller) {
        if (cache.containsKey(query)) {
            return cache.get(query);
        }
        R result = cacheFiller.apply(query);
        cache.put(query, result);
        return result;
    }

    private CardContainer getCardList(FindCardsByIdsQuery query) {
        return query.getCardIds().stream().map(this::getCardById).collect(collectionConsumer(CardContainer::new));
    }

    protected <T, R> Collector<T, Collection<T>, R> collectionConsumer(Function<Collection<T>, R> finisher) {
        return Collector.of(listSupplier(), Collection::add, collectionCombiner(), finisher);
    }

    protected <T> BinaryOperator<Collection<T>> collectionCombiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    protected <T> Supplier<Collection<T>> listSupplier() {
        return (Supplier<Collection<T>>) ArrayList::new;
    }

    private Card getCardById(Long cardId) {
        return repository.getById(cardId);
    }

}
