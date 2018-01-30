package com.dedale.cards.query;

import com.dedale.cards.Card;
import com.dedale.core.query.Query;


public class AddCard implements Query<Card> {

    private Card card;

    private AddCard(Card card) {
        this.card = card;
    }

    public static AddCard from(Card card) {
        return new AddCard(card);
    }

    public Card getCard() {
        return card;
    }
}
