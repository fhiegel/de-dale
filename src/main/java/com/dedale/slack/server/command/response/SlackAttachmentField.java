package com.dedale.slack.server.command.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SlackAttachmentField {

    private String title;
    private String value;

    @JsonProperty("short")
    private boolean isShort;

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    public boolean isShort() {
        return isShort;
    }

    void setShort(boolean isShort) {
        this.isShort = isShort;
    }

    // Object implementation methods


    @Override
    public String toString() {
        return "SlackMessageAttachmentField{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", isShort=" + isShort +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackAttachmentField that = (SlackAttachmentField) o;
        return isShort == that.isShort &&
                Objects.equals(title, that.title) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, value, isShort);
    }
}
