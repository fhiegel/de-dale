package com.dedale.cards.query;

import com.dedale.cards.Card;
import com.dedale.cards.CardBuilder;
import com.dedale.cards.CardRepository;
import com.dedale.cards.CardRepositoryInMemory;
import com.dedale.utils.jersey.ApplicationInjectionRule;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCardCommandHandlerTest {

    @Rule
    public ApplicationInjectionRule rule = ApplicationInjectionRule.dedaleRule().configureBinding(
            binder -> binder.bind(CardRepositoryInMemory.class).to(CardRepository.class).in(Singleton.class));

    @Inject
    private CardRepository repository;

    @Inject
    private AddCardCommandHandler handler;

    @Test
    public void should_add_card_add_it_with_id() throws Exception {
        // Given
        AddCardCommand addCommand = AddCardCommand.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card.getId()).isNotNull();
    }

    @Test
    public void should_add_card_add_it_in_repository() throws Exception {
        // Given
        AddCardCommand addCommand = AddCardCommand.from(CardBuilder.builder().build());

        // When
        Card card = handler.handle(addCommand);

        // Then
        assertThat(card).isEqualTo(repository.getById(card.getId()));
    }

}
