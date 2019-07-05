package com.dedale.cards;

import com.dedale.core.query.InMemoryRepository;

public class CardRepositoryInMemory extends InMemoryRepository<Card> implements Cards {

    public void clear() {
        getAll().forEach(this::delete);
    }
}
