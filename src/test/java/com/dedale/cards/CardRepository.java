package com.dedale.cards;

public interface CardRepository {

    Card get(long id);

    void add(Card card);
    
    void delete(Card card);
    
}
