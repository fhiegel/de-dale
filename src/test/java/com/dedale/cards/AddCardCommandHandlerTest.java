package com.dedale.cards;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.junit.Rule;
import org.junit.Test;

import com.dedale.utils.jersey.ApplicationInjectionRule;

public class AddCardCommandHandlerTest {
    
    @Rule
    public ApplicationInjectionRule rule = ApplicationInjectionRule
            .dedaleRule()
            .configureBinding(binder -> binder.bind(CardRepositoryInMemory.class).to(CardRepository.class).in(Singleton.class));
    
    @Inject
    private CardRepository repository;
    
    @Inject
    private AddCardCommandHandler handler;
    
    @Test
    public void should_add_card_add_it_with_id() throws Exception {
        // Given
        AddCardCommand addCommand = new AddCardCommand();
        
        // When
        Card card = handler.handle(addCommand);
        
        // Then
        assertThat(card.getId()).isNotNull();
    }
    
    @Test
    public void should_add_card_add_it_in_repository() throws Exception {
        // Given
        AddCardCommand addCommand = new AddCardCommand();
        
        // When
        Card card = handler.handle(addCommand);
        
        // Then
        assertThat(card).isEqualTo(repository.getById(card.getId()));
    }
    
}
