package com.dedale.cards;

public class CardBuilder {

    private long id;

    private String description;

    private String title;

    public static CardBuilder builder() {
        return new CardBuilder();
    }

    public CardBuilder id(long id) {
        this.id = id;
        return this;
    }

    public CardBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CardBuilder title(String title) {
        this.title = title;
        return this;
    }

    public Card build() {
        return new Card(id, title, description
        );
    }
}
