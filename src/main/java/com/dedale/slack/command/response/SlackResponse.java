package com.dedale.slack.command.response;

import static com.dedale.slack.command.response.SlackResponseType.EPHEMERAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@JsonInclude(Include.NON_EMPTY)
public class SlackResponse {

    private String token;// xxxx-xxxxxxxxx-xxxx Required
    private String channel;// C1234567890 Required
    private String text;

    @JsonProperty("response_type")
    private SlackResponseType responseType = EPHEMERAL;

    private List<SlackAttachment> attachments = new ArrayList<>();

    @JsonProperty("mrkdwn")
    private boolean markdown = true;

    @JsonProperty("username")
    private String userName;

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

    public List<SlackAttachment> getAttachments() {
        return attachments;
    }

    void setAttachments(List<SlackAttachment> attachments) {
        this.attachments = attachments;
    }

    void addAttachment(SlackAttachment attachment) {
        getAttachments().add(attachment);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    // Object implementation methods


    @Override
    public String toString() {
        return "SlackMessage{" +
                "token='" + token + '\'' +
                ", channel='" + channel + '\'' +
                ", text='" + text + '\'' +
                ", responseType=" + responseType +
                ", attachments=" + attachments +
                ", markdown=" + markdown +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackResponse that = (SlackResponse) o;
        return markdown == that.markdown &&
                Objects.equals(token, that.token) &&
                Objects.equals(channel, that.channel) &&
                Objects.equals(text, that.text) &&
                responseType == that.responseType &&
                Objects.deepEquals(attachments, that.attachments) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, channel, text, responseType, attachments, markdown, userName);
    }
}
