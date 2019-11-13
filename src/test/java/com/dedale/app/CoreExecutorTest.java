package com.dedale.app;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoreExecutorTest {

    private CoreExecutor executor = new CoreExecutor(new MainCommand(), List.of(new MeCommand()));

    @Test
    void should_get_main_content_when_execute_empty_command() {
        String command = "";

        CoreResult result = executor.execute(command);

        assertThat(result.toString()).isEqualTo("Main Command is the best");
    }

    @Test
    void should_execute_me_command() {
        String command = "me";

        CoreResult result = executor.execute(command);

        assertThat(result.toString()).isEqualTo("helloYou");
    }

}
