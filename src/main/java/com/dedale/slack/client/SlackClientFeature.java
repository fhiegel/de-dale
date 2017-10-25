package com.dedale.slack.client;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.dedale.slack.client.hermes.HermesSlackClient;
import com.dedale.slack.client.request.SlackRequestReader;
import com.dedale.slack.client.response.SlackResponseWriter;

public class SlackClientFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(SlackApp.class);
        context.register(SlackRequestReader.class);
        context.register(com.dedale.slack.client.response.legacy.SlackResponseWriter.class);
        context.register(SlackResponseWriter.class);
        context.register(HermesSlackClient.class);
        return true;
    }

}
