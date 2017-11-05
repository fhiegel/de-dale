package com.dedale.slack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.utils.resources.Resources;

public class SlackCredentialsTest {

    @Test
    public void should_load_credentials() throws Exception {
        SlackCredentials credentials = Resources.getRelativeTo(getClass(), "slack.credentials.sample.yaml").fromYaml().as(SlackCredentials.class);

        assertThat(credentials).isNotNull();
        assertThat(credentials.getToken("bot").get()).isEqualTo("bot_token");
    }

}
