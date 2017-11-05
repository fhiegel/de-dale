package com.dedale.slack.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SlackResponseType {
    
    @JsonProperty("ephemeral") EPHEMERAL,
    @JsonProperty("in_channel") IN_CHANNEL;

}
