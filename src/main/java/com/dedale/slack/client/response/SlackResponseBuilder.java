package com.dedale.slack.client.response;

public class SlackResponseBuilder {

    private SlackResponse bean;

    public SlackResponseBuilder() {
        this.bean = defaultSlackResponse();
    }

    private static SlackResponse defaultSlackResponse() {
        return new SlackResponse();
    }

    public SlackResponseBuilder withToken(String token) {
        bean.setToken(token);
        return this;
    }

    public SlackResponseBuilder withChannel(String channel) {
        bean.setChannel(channel);
        return this;
    }

    public SlackResponseBuilder withText(String text) {
        bean.setText(text);
        return this;
    }

    public SlackResponseBuilder withAs_user(boolean as_user) {
        bean.setAsUser(as_user);
        return this;
    }

    public SlackResponseBuilder withAttachments(String attachments) {
        bean.setAttachments(attachments);
        return this;
    }

    public SlackResponseBuilder withIcon_emoji(String icon_emoji) {
        bean.setIconEmoji(icon_emoji);
        return this;
    }

    public SlackResponseBuilder withIcon_url(String icon_url) {
        bean.setIconUrl(icon_url);
        return this;
    }

    public SlackResponseBuilder withLink_names(boolean link_names) {
        bean.setLinkNames(link_names);
        return this;
    }

    public SlackResponseBuilder withParse(String parse) {
        bean.setParse(parse);
        return this;
    }

    public SlackResponseBuilder withReply_broadcast(boolean reply_broadcast) {
        bean.setReplyBroadcast(reply_broadcast);
        return this;
    }

    public SlackResponseBuilder withThread_ts(String thread_ts) {
        bean.setThreadTimestamp(thread_ts);
        return this;
    }

    public SlackResponseBuilder withUnfurl_links(boolean unfurl_links) {
        bean.setUnfurlLinks(unfurl_links);
        return this;
    }

    public SlackResponseBuilder withUnfurl_media(boolean unfurl_media) {
        bean.setUnfurlMedia(unfurl_media);
        return this;
    }

    public SlackResponseBuilder withUsername(String username) {
        bean.setUsername(username);
        return this;
    }

    public SlackResponse build() {
        return bean;
    }

}