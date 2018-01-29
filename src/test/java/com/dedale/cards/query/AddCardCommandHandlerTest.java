package com.dedale.cards.query;

import com.dedale.cards.Card;
import com.dedale.cards.CardBuilder;
import com.dedale.cards.Cards;
import com.dedale.cards.CardRepositoryInMemory;
import com.dedale.utils.jersey.ApplicationInjectionRule;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCardCommandHandlerTest {

    private Cards repository = new CardRepositoryInMemory();

    @Rule
    public ApplicationInjectionRule rule = ApplicationInjectionRule
            .dedaleRule()
            .configureBinding(
                    binder -> binder.bind(repository).to(Cards.class)
            );

    @Inject
    private AddCardHandler handler;

    @Test
    public void should_add_card_add_it_with_id() {
        // Given
        AddCard addCommand = AddCard.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card.getId()).isNotNull();
    }

    @Test
    public void should_add_card_add_it_in_repository() {
        // Given
        AddCard addCommand = AddCard.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card).isEqualTo(repository.getById(card.getId()));
    }

}
