package com.dedale.slack.message;

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
public class SlackMessageAttachment {

    @JsonProperty("author_icon")
    private String authorIcon;

    @JsonProperty("author_link")
    private String authorLink;

    @JsonProperty("author_name")
    private String authorName;

    private String color;

    private String fallback;

    private List<SlackMessageAttachmentField> fields = new ArrayList<>();

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

    SlackMessageAttachment() {
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

    public List<SlackMessageAttachmentField> getFields() {
        return fields;
    }

    void setFields(List<SlackMessageAttachmentField> fields) {
        this.fields = fields;
    }

    void addField(SlackMessageAttachmentField field) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorIcon == null) ? 0 : authorIcon.hashCode());
        result = prime * result + ((authorLink == null) ? 0 : authorLink.hashCode());
        result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((fallback == null) ? 0 : fallback.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((footer == null) ? 0 : footer.hashCode());
        result = prime * result + ((footerIcon == null) ? 0 : footerIcon.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((markdownIn == null) ? 0 : markdownIn.hashCode());
        result = prime * result + ((pretext == null) ? 0 : pretext.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((thumbUrl == null) ? 0 : thumbUrl.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((titleLink == null) ? 0 : titleLink.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SlackMessageAttachment other = (SlackMessageAttachment) obj;
        if (authorIcon == null) {
            if (other.authorIcon != null)
                return false;
        } else if (!authorIcon.equals(other.authorIcon))
            return false;
        if (authorLink == null) {
            if (other.authorLink != null)
                return false;
        } else if (!authorLink.equals(other.authorLink))
            return false;
        if (authorName == null) {
            if (other.authorName != null)
                return false;
        } else if (!authorName.equals(other.authorName))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (fallback == null) {
            if (other.fallback != null)
                return false;
        } else if (!fallback.equals(other.fallback))
            return false;
        if (fields == null) {
            if (other.fields != null)
                return false;
        } else if (!fields.equals(other.fields))
            return false;
        if (footer == null) {
            if (other.footer != null)
                return false;
        } else if (!footer.equals(other.footer))
            return false;
        if (footerIcon == null) {
            if (other.footerIcon != null)
                return false;
        } else if (!footerIcon.equals(other.footerIcon))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (markdownIn == null) {
            if (other.markdownIn != null)
                return false;
        } else if (!markdownIn.equals(other.markdownIn))
            return false;
        if (pretext == null) {
            if (other.pretext != null)
                return false;
        } else if (!pretext.equals(other.pretext))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (thumbUrl == null) {
            if (other.thumbUrl != null)
                return false;
        } else if (!thumbUrl.equals(other.thumbUrl))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (titleLink == null) {
            if (other.titleLink != null)
                return false;
        } else if (!titleLink.equals(other.titleLink))
            return false;
        return true;
    }

}