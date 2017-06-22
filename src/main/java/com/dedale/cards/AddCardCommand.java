package com.dedale.cards;

import lombok.Getter;

@Getter
public class AddCardCommand implements Query {

    private Card card;

    private AddCardCommand(Card card) {
        this.card = card;
    }

    public static AddCardCommand from(Card card) {
        return new AddCardCommand(card);
    }

}
