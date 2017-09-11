package com.dedale.hermes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HermesTokenizerTest {

    private static final String DEFAULT_AUTHOR = "Dummy the dwarf";
    private static final String AN_AUTHOR = "Tolkien";

    @Test
    public void should_have_no_next_when_empty() throws Exception {
        // Given
        HermesTokenizer hermesCommand = new HermesTokenizer("", DEFAULT_AUTHOR);

        // Then
        assertThat(hermesCommand.hasNext()).isFalse();
    }

    @Test
    public void should_have_next() throws Exception {
        // Given
        HermesTokenizer tokenizer = new HermesTokenizer("1d20+5", DEFAULT_AUTHOR);

        // Then
        assertThat(tokenizer.hasNext()).isTrue();
    }

    @Test
    public void should_command_contains_command_string() throws Exception {
        // Given
        HermesTokenizer tokenizer = new HermesTokenizer("1d20+5", DEFAULT_AUTHOR);

        // When
        HermesCommand command = tokenizer.nextCommand();

        // Then
        assertThat(command.command).isEqualTo("1d20+5");
    }

    @Test
    public void should_command_contains_default_author() throws Exception {
        // Given
        HermesTokenizer command = new HermesTokenizer("1d20+5", DEFAULT_AUTHOR);

        // When
        HermesCommand nextCommand = command.nextCommand();

        // Then
        assertThat(nextCommand.author).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    public void should_first_command_contains_first_sentence() throws Exception {
        // Given
        HermesTokenizer tokenizer = new HermesTokenizer("1d20+5 1d20+6", DEFAULT_AUTHOR);

        // When
        HermesCommand firstCommand = tokenizer.nextCommand();

        // Then
        assertThat(firstCommand.command).isEqualTo("1d20+5");
    }

    @Test
    public void should_secon_command_contains_second_sentence() throws Exception {
        // Given
        HermesTokenizer tokenizer = new HermesTokenizer("1d20+5 1d20+6", DEFAULT_AUTHOR);

        // When
        tokenizer.nextCommand();
        HermesCommand secondCommand = tokenizer.nextCommand();

        // Then
        assertThat(secondCommand.command).isEqualTo("1d20+6");
    }
    
    @Test
    public void should_author_argument_updates_author() throws Exception {
        // Given
        HermesTokenizer command = new HermesTokenizer("1d20+5 --author=\"" + AN_AUTHOR + "\"", DEFAULT_AUTHOR);

        // When
        HermesCommand nextCommand = command.nextCommand();

        // Then
        assertThat(nextCommand.author).isEqualTo(AN_AUTHOR);
    }


}
