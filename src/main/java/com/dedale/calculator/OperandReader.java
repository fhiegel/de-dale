package com.dedale.calculator;

enum OperandReader {
    
    LEFT_TO_RIGHT(OperandReader::leftOfLeftmost, OperandReader::rightOfLeftmost),
    
    RIGHT_TO_LEFT(OperandReader::leftOfRightmost, OperandReader::rightOfRightmost);
    
    @FunctionalInterface
    interface OperandExtractor {
        String extract(String sentence, String regex);
    }
    
    private static final String ESCAPE_PREFIX = "\\";
    
    private OperandExtractor left;
    private OperandExtractor right;
    
    private OperandReader(OperandExtractor left, OperandExtractor right) {
        this.left = left;
        this.right = right;
    }
    
    public String readLeftOperand(String sentence, String symbol) {
        return left.extract(sentence, ESCAPE_PREFIX + symbol);
    }
    
    public String readRightOperand(String sentence, String symbol) {
        return right.extract(sentence, ESCAPE_PREFIX + symbol);
    }
    
    private static String leftOfLeftmost(String sentence, String regex) {
        return sentence.split(regex, 2)[0];
    }
    
    private static String rightOfLeftmost(String sentence, String regex) {
        return sentence.split(regex, 2)[1];
    }
    
    private static String leftOfRightmost(String sentence, String regex) {
        return sentence.replaceFirst("[" + regex + "]([^" + regex + "]+)$", "");
    }
    
    private static String rightOfRightmost(String sentence, String regex) {
        return sentence.replaceAll("^(.+)[" + regex + "]", "");
    }
}