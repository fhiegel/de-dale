package com.dedale.slack.client;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.dedale.slack.client.request.SlackRequestReader;

public class SlackClientFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(SlackRequestReader.class);
        return true;
    }

}
