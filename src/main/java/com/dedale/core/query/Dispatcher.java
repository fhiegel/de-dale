package com.dedale.core.query;

import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Dispatcher {

    private static final boolean NOT_PARALLEL = false;

    private final Iterable<QueryHandler<?, ?>> handlers;

    @Inject
    public Dispatcher(Iterable<QueryHandler<?, ?>> handlers) {
        this.handlers = handlers;
    }

    public <R, Q extends Query<R>> R dispatch(Q query) {
        return getHandler(query).handle(query);
    }

    @SuppressWarnings("unchecked")
    private <R, Q extends Query<R>> QueryHandler<R, Q> getHandler(Q query) {
        return (QueryHandler<R, Q>) StreamSupport
                .stream(handlers.spliterator(), NOT_PARALLEL)
                .filter(isApplicableToQuery(query))
                .findFirst()
                .get();
    }

    private Predicate<? super QueryHandler<?, ?>> isApplicableToQuery(Query<?> query) {
        return handler -> handler.listenTo().equals(query.getClass());
    }

}
