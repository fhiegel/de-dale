package com.dedale.slack.client.hermes;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dedale.hermes.HermesEngine;
import com.dedale.hermes.HermesResponse;
import com.dedale.hermes.HermesTokenizer;
import com.dedale.slack.client.request.SlackRequest;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

@Path("slack/hermes")
public class HermesSlackClient {
    
    private HermesEngine hermes;

    @Inject
    public HermesSlackClient(HermesEngine hermes) {
        this.hermes = hermes;
    }

    @POST
    public SlackResponse slackRoll(SlackRequest slackRequest) {
        HermesTokenizer command = mapToHermesCommand(slackRequest);
        HermesResponse response = hermes.dispatch(command);
        return mapFromHermesResponse(response);
    }

    private SlackResponse mapFromHermesResponse(HermesResponse response) {
        SlackResponseBuilder slackResponse = SlackResponseBuilder.beginResponse();
        return response.accept(slackResponse);
    }

    private HermesTokenizer mapToHermesCommand(SlackRequest slackRequest) {
        return new HermesTokenizer(slackRequest.getText(), slackRequest.getUserName());
    }
    
}
