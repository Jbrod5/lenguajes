
package com.jbrod.analizer.lexer;

/**
 *
 * @author Jorge
 */
public class Token {
    private Tokens tokenType; //Enum  
    private String lexeme; 
    
    private String pattern; 
    private int row; 
    private int column; 
    
    //Constructor de pruebas
    public Token (Tokens type, String lexeme){
        this.tokenType = type; 
        this.lexeme = lexeme; 
    }
    
    public Token(Tokens type, String lexeme, String pattern, int row, int column){
        this.tokenType = type; 
        this.lexeme = lexeme; 
        this.pattern = pattern; 
        this.row = row; 
        this.column = column;
    }

    
    
    
    // -------------------- GETTERS / SETTERS -------------------------
    public Tokens getTokenType() {
        return tokenType;
    }

    public void setTokenType(Tokens tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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
