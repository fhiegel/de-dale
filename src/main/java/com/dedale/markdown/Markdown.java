package com.dedale.markdown;

public class Markdown {

    private final String markdown;

    public Markdown() {
        this("");
    }

    private Markdown(String markdown) {
        this.markdown = markdown;
    }

    public static Markdown of(String text) {
        return new Markdown(text);
    }

    public Markdown bold() {
        return append(MarkdownTags.BOLD);
    }

    public Markdown italic() {
        return append(MarkdownTags.ITALIC);
    }

    public Markdown code() {
        return append(MarkdownTags.CODE);
    }

    public Markdown codeblock() {
        return append(MarkdownTags.CODEBLOCK);
    }

    public Markdown line() {
        return append("\n");
    }

    public Markdown append(String str) {
        return new Markdown(markdown + str);
    }

    @Override
    public String toString() {
        return markdown;
    }

}
