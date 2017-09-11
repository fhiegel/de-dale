package com.dedale.hermes;

public class HermesDiceResult {
    enum HermesResponseCode {
        ERROR,
        VALID;
    }

    private HermesResponseCode code;
    private String text;
    private String author;


    public HermesDiceResult(HermesResponseCode code, String text, String author) {
        this.code = code;
        this.text = text;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
    
    public boolean isValid(){
        return HermesResponseCode.VALID.equals(code);
    }

}
