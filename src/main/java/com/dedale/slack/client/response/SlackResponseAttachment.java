package com.dedale.slack.client.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dedale.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class SlackResponseAttachment {

    @JsonProperty("author_icon")
    private String authorIcon;

    @JsonProperty("author_link")
    private String authorLink;

    @JsonProperty("author_name")
    private String authorName;

    private String color;

    private String fallback;

    private List<SlackResponseAttachmentField> fields = new ArrayList<>();

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

    SlackResponseAttachment() {
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

    public List<SlackResponseAttachmentField> getFields() {
        return fields;
    }

    void setFields(List<SlackResponseAttachmentField> fields) {
        this.fields = fields;
    }

    void addField(SlackResponseAttachmentField field) {
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
        return getClass().getSimpleName() + " " + JsonUtils.asJson(this);
    }

}