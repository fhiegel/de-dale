package com.dedale.hades.characters;

import java.util.ArrayList;
import java.util.List;

public class CreateCharacter {
    private final String characterName;

    public CreateCharacter(String characterName) {
        this.characterName = characterName;
    }

    public List<String> updateState(List<String> initialState) {
        ArrayList<String> mutableState = new ArrayList<>(initialState);
        mutableState.add(characterName);
        return List.copyOf(mutableState);
    }
}
