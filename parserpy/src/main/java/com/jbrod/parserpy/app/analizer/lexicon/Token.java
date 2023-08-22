
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
    
    public String getGraphHtml(){
        String graph ="";
        
        String color = switch(tokenType){
            case "ARITHMETIC", "COMPARISION","LOGICAL", "ASSIGNAMENT" -> "blue";
            case "KEYWORD"              -> "purple" ;
            case "CONSTANT"             -> "red"    ;
            case "COMMENT"              -> "grey"   ;
            case "OTHERS"               -> "green"  ; 
            case "IDENTIFIER"           -> "yellow"  ;
                
            //Caso especial, lexema desconocido    
            case "LEXICAL_ERROR_unknow_lexeme" -> "orange";
            default -> "orange";
                
        };
        
        //Estilo comun para TODOS los nodos del token
        String style = "style=\"    width: 50px; background-color:" + color + "; border: 2px solid black; margin: 2px; border-radius: 100%; display: inline-block; padding: 10px;\"";
        
        //Cabecera: div del token 
        graph += " <div style=\" display: flex; flex-wrap: wrap; border: 2px solid black;margin:4px; text-align: center;\">"
                + "<h1>" + tokenType + ": " + lexeme + "</h1>"; 
        
        //crear div de cada parte del lexema
        for (int i = 0; i < lexeme.length(); i++) {
            //verificar si no es el final 
            if(i < lexeme.length() - 1){
                graph += "<div " + style + "> " + lexeme.charAt(i) + "</div> &rarr;";
            }else{
                //display: inline-block; 
                graph += "<div style=\"display: flex; width: 60px;border: 2px solid black; margin: 2px; border-radius: 20px ; width: auto; display: inline-block; padding: 10px\">";
                graph += "<div " + style + "> " + lexeme.charAt(i) + "</div>";
                graph += "</div>";
            }
            
            
        }
        
        graph += "</div> <br/> <br/>\n\r";
        //cerrar cabecera
        return graph; 
    }
    
}
