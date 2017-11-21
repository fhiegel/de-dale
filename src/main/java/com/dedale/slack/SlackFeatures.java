package com.dedale.slack;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.dedale.slack.api.SlackApp;
import com.dedale.slack.api.hermes.HermesSlackClient;
import com.dedale.slack.client.SlackClient;
import com.dedale.slack.message.SlackMessageWriter;
import com.dedale.slack.request.SlackRequestReader;
import com.dedale.utils.resources.Resources;

public class SlackFeatures implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(SlackApp.class);
        context.register(SlackRequestReader.class);
        context.register(SlackMessageWriter.class);
        context.register(HermesSlackClient.class);

        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                SlackCredentials credentials = Resources.getRelativeTo(getClass(), "slack.credentials.yaml").fromYaml()
                        .as(SlackCredentials.class);

                bind(credentials).to(SlackCredentials.class);
                bind(SlackClient.class).to(SlackClient.class);
            }
        });

        context.register(SlackLifecycleListener.class);
        return true;
    }

}
