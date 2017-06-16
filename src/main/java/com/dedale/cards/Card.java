package com.dedale.cards;

import com.dedale.common.Identifiable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Card implements Identifiable {
    
    private long id;
    
    Card() {
    }
    
    public Card(long cardId) {
        this.id = cardId;
    }
    
    @Override
    public void setId(long id) {
        this.id = id;
    }
    
}
