package com.dedale.cards;

import java.util.List;

import javax.inject.Inject;

public class GetAllCardsQueryHandler implements QueryHandler<List<Card>, GetAllCardsQuery> {

    @Inject
    private CardRepository repository;

    public List<Card> handle(GetAllCardsQuery getAllCardsCommand) {
        return repository.getAll();
    }

}
