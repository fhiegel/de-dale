package com.dedale.cards.query;

import org.jvnet.hk2.annotations.Service;

import com.dedale.cards.CardContainer;
import com.dedale.cards.Cards;
import com.dedale.core.query.QueryHandler;

import javax.inject.Inject;

@Service
public class GetAllCardsHandler implements QueryHandler<CardContainer, GetAllCards> {

    private final Cards cards;

    @Inject
    public GetAllCardsHandler(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Class<GetAllCards> listenTo() {
        return GetAllCards.class;
    }

    public CardContainer handle(GetAllCards getAllCardsCommand) {
        return new CardContainer(cards.getAll());
    }


}
