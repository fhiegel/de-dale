package com.dedale.cards;

import javax.inject.Singleton;

import com.dedale.core.query.InMemoryRepository;

@Singleton
public class CardRepositoryInMemory extends InMemoryRepository<Card> implements Cards {
}
