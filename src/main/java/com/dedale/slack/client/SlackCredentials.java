package com.dedale.slack.client;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.Map;
import java.util.Optional;

@ConfigurationProperties("slack.credentials")
public class SlackCredentials {
    public static final String BOT_TOKEN = "bot";

    private Map<String, String> tokens = Map.of();

    public Map<String, String> getTokens() {
        return tokens;
    }

    public void setTokens(Map<String, String> tokens) {
        this.tokens = tokens;
    }

    public String getToken(String tokenName) {
        return tokens.get(tokenName);
    }

}
