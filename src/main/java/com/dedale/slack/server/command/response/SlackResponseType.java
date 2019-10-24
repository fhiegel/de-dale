package com.dedale.slack.server.command.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SlackResponseType {
    
    @JsonProperty("ephemeral") EPHEMERAL,
    @JsonProperty("in_channel") IN_CHANNEL

}
