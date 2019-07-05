package com.dedale.cards.query;

import com.dedale.cards.Card;
import com.dedale.cards.Cards;
import com.dedale.core.query.QueryHandler;

import javax.inject.Inject;

public class AddCardHandler implements QueryHandler<Card, AddCard> {

    private final Cards cards;

    public AddCardHandler(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Class<AddCard> listenTo() {
        return AddCard.class;
    }

    @Override
    public Card handle(AddCard query) {
        Card card = query.getCard();
        cards.add(card);
        return card;
    }

}
