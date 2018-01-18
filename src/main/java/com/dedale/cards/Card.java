package com.dedale.cards;

import com.dedale.common.Identifiable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javafx.scene.control.PopupControlBuilder;

@JsonInclude(Include.NON_EMPTY)
public class Card implements Identifiable {

    private long id;
    private String title;
    private String description;

    public Card() {
    }

    public Card(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
