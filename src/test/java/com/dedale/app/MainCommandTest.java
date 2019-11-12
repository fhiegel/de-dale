package com.dedale.app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainCommandTest {

    @Test
    void should_execute_naive_command() {
        String command = "me";
        Object result = MainExecutor.execute(command);
        assertThat(result).isEqualTo("helloYou");
    }

}
