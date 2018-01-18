package com.dedale.cards.query;

import com.dedale.cards.Card;
import com.dedale.common.Query;


public class AddCardCommand implements Query<Card> {

    private Card card;

    private AddCardCommand(Card card) {
        this.card = card;
    }

    public static AddCardCommand from(Card card) {
        return new AddCardCommand(card);
    }

    public Card getCard() {
        return card;
    }
}
