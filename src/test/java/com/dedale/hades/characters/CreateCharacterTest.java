package com.dedale.hades.characters;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateCharacterTest {

    @Test
    void should_return_initial_state_when_no_action_given() {
        List<String> initialState = List.of();

        List<String> actualState = reduce(initialState, null);

        assertThat(actualState)
                .isEmpty();
    }

    @Test
    void should_add_character_to_state_when_create_character_is_dispatched() {
        CreateCharacter createCharacter = new CreateCharacter("Nestor");
        List<String> initialState = List.of();

        List<String> actualState = reduce(initialState, createCharacter);

        assertThat(actualState)
                .contains("Nestor");
    }

    private List<String> reduce(List<String> initialState, CreateCharacter createCharacter) {
        if(createCharacter == null) {
            return initialState;
        }
        return createCharacter.updateState(initialState);
    }
}