
package com.jbrod.parserpy.app.analizer.lexicon;

public class Token {
    private String tokenType;   //puede ser un enum 
    private String pattern; 
    private String lexeme;
    private int row; 
    private int column; 

    public Token(String tokenType, String pattern, String lexeme, int row, int column) {
        this.tokenType = tokenType;
        this.pattern = pattern;
        this.lexeme = lexeme;
        this.row = row;
        this.column = column;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }    
    
    
}
