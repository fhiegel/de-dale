package com.dedale.slack.client.response.legacy;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SlackResponseType {
    
    @JsonProperty("ephemeral") EPHEMERAL,
    @JsonProperty("in_channel") IN_CHANNEL;

}
