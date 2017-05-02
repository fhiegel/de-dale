package com.dedale.slack.client.response;

import static com.dedale.slack.client.response.SlackResponseType.EPHEMERAL;

import java.util.List;

import com.dedale.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <b>Message Formatting</b>
 * <p>
 * Slack messages may be formatted using a simple markup language similar to
 * Markdown. Supported formatting includes: ```pre```, `code`, _italic_, *bold*,
 * and even ~strike~.; full details are available on our help site.
 * <p>
 * By default bot message text will be formatted, but attachments are not. To
 * disable formatting on a non-user message, set the mrkdwn property to false.
 * To enable formatting on attachment fields, set the mrkdwn_in array on each
 * attachment to the list of fields to process. Some examples:
 */
@JsonInclude(Include.NON_NULL)
public class SlackResponse {

    private String text;

    @JsonProperty("response_type")
    private SlackResponseType responseType = EPHEMERAL;

    private List<String> attachments;

    SlackResponse() {
    }

    public String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    public SlackResponseType getResponseType() {
        return responseType;
    }

    void setResponseType(SlackResponseType responseType) {
        this.responseType = responseType;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    // Object implementation methods
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + JsonUtils.asJson(this);
    }

}
