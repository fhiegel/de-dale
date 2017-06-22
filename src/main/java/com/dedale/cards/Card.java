package com.dedale.cards;

import com.dedale.common.Identifiable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(Include.NON_EMPTY)
public class Card implements Identifiable {

    private long id;
    private String title;
    private String description;

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
