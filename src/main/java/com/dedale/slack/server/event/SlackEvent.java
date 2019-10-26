package com.dedale.slack.server.event;

import java.util.Objects;

public class SlackEvent {

    private String token;
    private String type;
    private String challenge;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChallenge() {
        return challenge;
    }

    void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    // Object implementation methods

    @Override
    public String toString() {
        return "SlackEvent{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", challenge='" + challenge + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackEvent that = (SlackEvent) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(type, that.type) &&
                Objects.equals(challenge, that.challenge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, challenge);
    }
}
