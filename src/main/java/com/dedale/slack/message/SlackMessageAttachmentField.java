package com.dedale.slack.message;

import com.dedale.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SlackMessageAttachmentField {

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
        return getClass().getSimpleName() + " " + JsonUtils.asJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackMessageAttachmentField that = (SlackMessageAttachmentField) o;
        return isShort == that.isShort &&
                Objects.equals(title, that.title) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, value, isShort);
    }
}
