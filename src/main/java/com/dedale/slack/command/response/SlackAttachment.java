package com.dedale.slack.command.response;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class SlackAttachment {

    @JsonProperty("author_icon")
    private String authorIcon;

    @JsonProperty("author_link")
    private String authorLink;

    @JsonProperty("author_name")
    private String authorName;

    private String color;

    private String fallback;

    private List<SlackAttachmentField> fields = new ArrayList<>();

    private String footer;

    @JsonProperty("footer_icon")
    private String footerIcon;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("mrkdwn_in")
    private Set<String> markdownIn = new HashSet<>();

    private String pretext;

    private String text;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    private String title;

    @JsonProperty("title_link")
    private String titleLink;

    @JsonProperty("ts")
    private Date timestamp;

    SlackAttachment() {
    }

    public String getAuthorIcon() {
        return authorIcon;
    }

    void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getAuthorName() {
        return authorName;
    }

    void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    public String getFallback() {
        return fallback;
    }

    void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public List<SlackAttachmentField> getFields() {
        return fields;
    }

    void setFields(List<SlackAttachmentField> fields) {
        this.fields = fields;
    }

    void addField(SlackAttachmentField field) {
        getFields().add(field);
    }

    public String getFooter() {
        return footer;
    }

    void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFooterIcon() {
        return footerIcon;
    }

    void setFooterIcon(String footerIcon) {
        this.footerIcon = footerIcon;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<String> getMarkdownIn() {
        return markdownIn;
    }

    void setMarkdownIn(Set<String> markdownIn) {
        this.markdownIn = markdownIn;
    }

    void addMarkdownField(String field) {
        getMarkdownIn().add(field);
    }

    public String getPretext() {
        return pretext;
    }

    void setPretext(String pretext) {
        this.pretext = pretext;
    }

    public String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Object implementation methods

    @Override
    public String toString() {
        return "SlackMessageAttachment{" +
                "authorIcon='" + authorIcon + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", authorName='" + authorName + '\'' +
                ", color='" + color + '\'' +
                ", fallback='" + fallback + '\'' +
                ", fields=" + fields +
                ", footer='" + footer + '\'' +
                ", footerIcon='" + footerIcon + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", markdownIn=" + markdownIn +
                ", pretext='" + pretext + '\'' +
                ", text='" + text + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackAttachment that = (SlackAttachment) o;
        return Objects.equals(authorIcon, that.authorIcon) &&
                Objects.equals(authorLink, that.authorLink) &&
                Objects.equals(authorName, that.authorName) &&
                Objects.equals(color, that.color) &&
                Objects.equals(fallback, that.fallback) &&
                Objects.deepEquals(fields, that.fields) &&
                Objects.equals(footer, that.footer) &&
                Objects.equals(footerIcon, that.footerIcon) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(markdownIn, that.markdownIn) &&
                Objects.equals(pretext, that.pretext) &&
                Objects.equals(text, that.text) &&
                Objects.equals(thumbUrl, that.thumbUrl) &&
                Objects.equals(title, that.title) &&
                Objects.equals(titleLink, that.titleLink) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorIcon, authorLink, authorName, color, fallback, fields, footer, footerIcon, imageUrl, markdownIn, pretext, text, thumbUrl, title, titleLink, timestamp);
    }
}