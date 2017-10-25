package com.dedale.slack.client.response;

public class SlackResponseBuilder {

    private SlackResponse response;

    public SlackResponseBuilder() {
        this.response = defaultSlackResponse();
    }

    private static SlackResponse defaultSlackResponse() {
        return new SlackResponse();
    }
    
    public static SlackResponseBuilder beginResponse() {
        return new SlackResponseBuilder();
    }

    public SlackResponseBuilder withToken(String token) {
        response.setToken(token);
        return this;
    }

    public SlackResponseBuilder withChannel(String channel) {
        response.setChannel(channel);
        return this;
    }

    public SlackResponseBuilder withText(String text) {
        response.setText(text);
        return this;
    }

    public SlackResponseBuilder withAs_user(boolean as_user) {
        response.setAsUser(as_user);
        return this;
    }

    public SlackResponseBuilder withAttachments(String attachments) {
        response.setAttachments(attachments);
        return this;
    }

    public SlackResponseBuilder withIcon_emoji(String icon_emoji) {
        response.setIconEmoji(icon_emoji);
        return this;
    }

    public SlackResponseBuilder withIcon_url(String icon_url) {
        response.setIconUrl(icon_url);
        return this;
    }

    public SlackResponseBuilder withLink_names(boolean link_names) {
        response.setLinkNames(link_names);
        return this;
    }

    public SlackResponseBuilder withParse(String parse) {
        response.setParse(parse);
        return this;
    }

    public SlackResponseBuilder withReply_broadcast(boolean reply_broadcast) {
        response.setReplyBroadcast(reply_broadcast);
        return this;
    }

    public SlackResponseBuilder withThread_ts(String thread_ts) {
        response.setThreadTimestamp(thread_ts);
        return this;
    }

    public SlackResponseBuilder withUnfurl_links(boolean unfurl_links) {
        response.setUnfurlLinks(unfurl_links);
        return this;
    }

    public SlackResponseBuilder withUnfurl_media(boolean unfurl_media) {
        response.setUnfurlMedia(unfurl_media);
        return this;
    }

    public SlackResponseBuilder withUsername(String username) {
        response.setUsername(username);
        return this;
    }

    public SlackResponse build() {
        return response;
    }

}