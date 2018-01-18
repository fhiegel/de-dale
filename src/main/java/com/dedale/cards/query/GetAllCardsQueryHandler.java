package com.dedale.cards.query;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.dedale.cards.CardContainer;
import com.dedale.cards.CardRepository;
import com.dedale.common.QueryHandler;

@Service
public class GetAllCardsQueryHandler implements QueryHandler<CardContainer, GetAllCardsQuery> {

    @Inject
    private CardRepository repository;

    @Override
    public Class<GetAllCardsQuery> listenTo() {
        return GetAllCardsQuery.class;
    }

    public CardContainer handle(GetAllCardsQuery getAllCardsCommand) {
        return new CardContainer(repository.getAll());
    }


}
