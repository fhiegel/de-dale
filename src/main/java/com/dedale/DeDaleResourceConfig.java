package com.dedale;

import com.dedale.slack.SlackCredentials;
import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Factory;

@Factory
public class DeDaleResourceConfig {

    public SlackCredentials getSlackCredentials() {

        SlackCredentials credentials = new SlackCredentials();
        BeanContext.run()
                .registerSingleton(SlackCredentials.class, credentials);
//        SlackCredentials slackCredentials = Resources.getRelativeTo(getClass(), "slack.credentials.yaml").fromYaml()
//                .as(SlackCredentials.class);
        return credentials;
    }

}
