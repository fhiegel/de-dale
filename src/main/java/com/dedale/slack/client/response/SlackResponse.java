package com.dedale.slack.client.response;

public class SlackResponse {

    private String token;// xxxx-xxxxxxxxx-xxxx Required
    private String channel;// C1234567890 Required
    private String text;// Hello world Required

    private boolean as_user;// true Optional
    private String attachments;// [{“pretext”: “pre-hello”, “text”: “text-world”}] Optional
    private String icon_emoji;// :chart_with_upwards_trend: Optional
    private String icon_url;// http://lorempixel.com/48/48 (1kB) Optional
    private boolean link_names;// true Optional
    private String parse;// full Optional
    private boolean reply_broadcast;// true Optional
    private String thread_ts;// 1234567890.123456 Optional
    private boolean unfurl_links;// true Optional
    private boolean unfurl_media;// false Optional
    private String username;// My Bot Optional

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAs_user() {
        return as_user;
    }

    public void setAsUser(boolean as_user) {
        this.as_user = as_user;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public void setIconEmoji(String icon_emoji) {
        this.icon_emoji = icon_emoji;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIconUrl(String icon_url) {
        this.icon_url = icon_url;
    }

    public boolean isLink_names() {
        return link_names;
    }

    public void setLinkNames(boolean link_names) {
        this.link_names = link_names;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public boolean isReply_broadcast() {
        return reply_broadcast;
    }

    public void setReplyBroadcast(boolean reply_broadcast) {
        this.reply_broadcast = reply_broadcast;
    }

    public String getThread_ts() {
        return thread_ts;
    }

    public void setThreadTimestamp(String thread_ts) {
        this.thread_ts = thread_ts;
    }

    public boolean isUnfurl_links() {
        return unfurl_links;
    }

    public void setUnfurlLinks(boolean unfurl_links) {
        this.unfurl_links = unfurl_links;
    }

    public boolean isUnfurl_media() {
        return unfurl_media;
    }

    public void setUnfurlMedia(boolean unfurl_media) {
        this.unfurl_media = unfurl_media;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
