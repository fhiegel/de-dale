package com.dedale.cards;

import lombok.Getter;

@Getter
public class Card {
    
    private String cardId;
    
    public Card(String cardId) {
        this.cardId = cardId;
    }
    
}
