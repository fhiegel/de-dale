package com.dedale.hermes;

public class HermesCommand {

    final String command;
    final String author;

    public HermesCommand(String hermesSentence, String defaultAuthor) {
        this.command = hermesSentence;
        this.author = defaultAuthor;
    }

}
