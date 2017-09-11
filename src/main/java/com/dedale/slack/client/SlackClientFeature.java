package com.dedale.slack.client;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.dedale.hermes.HermesEngine;
import com.dedale.slack.client.hermes.HermesSlackClient;
import com.dedale.slack.client.request.SlackRequestReader;
import com.dedale.slack.client.response.SlackResponseWriter;

public class SlackClientFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(SlackRequestReader.class);
        context.register(SlackResponseWriter.class);
        
        context.register(new HermesSlackClient(new HermesEngine()));
        return true;
    }

}
