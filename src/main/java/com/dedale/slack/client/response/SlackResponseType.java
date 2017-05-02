package com.dedale.slack.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

enum SlackResponseType {
    
    @JsonProperty("ephemeral") EPHEMERAL,
    @JsonProperty("in_channel") IN_CHANNEL;

}
