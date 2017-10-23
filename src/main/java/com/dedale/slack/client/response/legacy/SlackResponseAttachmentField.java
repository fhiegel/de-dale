package com.dedale.slack.client.response.legacy;

import com.dedale.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackResponseAttachmentField {

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

}
