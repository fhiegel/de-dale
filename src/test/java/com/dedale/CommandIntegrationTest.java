package com.dedale;

import com.dedale.app.CoreExecutor;
import com.dedale.app.CoreResult;
import com.dedale.app.Echo;
import com.dedale.slack.server.command.SlackCommand;
import com.dedale.slack.server.command.SlackCommandBuilder;
import com.dedale.slack.server.command.SlackCommandHandler;
import com.dedale.slack.server.command.response.SlackResponse;
import com.dedale.slack.server.command.response.SlackResponseBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandIntegrationTest {
    private CoreExecutor executor = new CoreExecutor(new Echo(), List.of());

    @Test
    void should_get_main_content_when_execute_empty_command() {

        SlackCommandHandler h
                = command -> {
                    CoreResult result = executor.execute(command.getText().split(" "));
                    return SlackResponseBuilder.aResponse()
                            .withText(result.toString())
                            .build();
                };
        SlackResponse resoponse = h.handle(new SlackCommandBuilder()
                .withText("command line")
                .asRequest());

        assertThat(resoponse.getText()).isEqualTo("```" +
                "command line" +
                "```");
    }

}