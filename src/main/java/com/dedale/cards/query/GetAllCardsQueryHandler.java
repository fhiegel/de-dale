package com.dedale.cards.query;

import java.util.List;

import javax.inject.Inject;

import com.dedale.cards.Card;
import com.dedale.cards.CardRepository;
import com.dedale.common.QueryHandler;

public class GetAllCardsQueryHandler implements QueryHandler<List<Card>, GetAllCardsQuery> {

    @Inject
    private CardRepository repository;

    public List<Card> handle(GetAllCardsQuery getAllCardsCommand) {
        return repository.getAll();
    }

    @Override
    public Class<GetAllCardsQuery> listenTo() {
        return GetAllCardsQuery.class;
    }

}
