package com.dedale.cards.query;

import com.dedale.cards.*;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class AddCardCommandHandlerTest {

    @Factory
    static class Mocks {
        @Singleton
        @Replaces(value = Cards.class, factory = CardFeature.class)
        Cards cards() {
            Cards repository = new CardRepositoryInMemory();
            return repository;
        }
    }

    @Inject
    private AddCardHandler handler;

    @Inject
    private Cards repository;

    @Test
    void should_add_card_add_it_with_id() {
        // Given
        AddCard addCommand = AddCard.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card.getId()).isNotNull();
    }

    @Test
    void should_add_card_add_it_in_repository() {
        // Given
        AddCard addCommand = AddCard.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card).isEqualTo(repository.getById(card.getId()));
    }

}
