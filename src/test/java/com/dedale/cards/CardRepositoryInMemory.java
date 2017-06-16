package com.dedale.cards;

import javax.inject.Singleton;

import com.dedale.common.InMemoryRepository;

@Singleton
public class CardRepositoryInMemory extends InMemoryRepository<Card> implements CardRepository {
}
