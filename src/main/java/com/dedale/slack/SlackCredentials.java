package com.dedale.slack;

import java.util.Map;
import java.util.Optional;

public class SlackCredentials {

    private String defaultChannel;
    private String technicalChannel;
    private Map<String, String> tokens;

    public Map<String, String> getTokens() {
        return tokens;
    }

    public void setTokens(Map<String, String> tokens) {
        this.tokens = tokens;
    }

    public Optional<String> getToken(String tokenName) {
        return Optional.ofNullable(tokens.get(tokenName));
    }

    public String getDefaultChannel() {
        return defaultChannel;
    }

    public void setDefaultChannel(String defaultChannel) {
        this.defaultChannel = defaultChannel;
    }

    public String getTechnicalChannel() {
        return technicalChannel;
    }

    public void setTechnicalChannel(String technicalChannel) {
        this.technicalChannel = technicalChannel;
    }

}
