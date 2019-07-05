package com.dedale.slack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.dedale.utils.resources.Resources;

public class SlackCredentialsTest {

    @Test
    public void should_load_credentials() {
        SlackCredentials credentials = Resources.getRelativeTo(getClass(), "slack.credentials.sample.yaml").fromYaml().as(SlackCredentials.class);

        assertThat(credentials).isNotNull();
        assertThat(credentials.getToken("bot")).isEqualTo("bot_token");
    }

}
