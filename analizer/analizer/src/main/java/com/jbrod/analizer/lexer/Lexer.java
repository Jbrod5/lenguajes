
package com.jbrod.analizer.lexer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que contiene la lista de tokens encontrados y el Tokenizer.
 * @author Jorge
 */
public class Lexer {
    List<Token> tokenList;
    Tokenizer tokenizer; 
    
    public Lexer(){
        tokenList = new LinkedList<>();
    }
    
    /**
     * Analiza el codigo completo pasado a través de un String y agrega los tokens detectados a una lista TokenList.
     * @param code: String con el codigo completo a analizar.
     * @throws IOException.
     **/
    public void Analize(String code) throws IOException{
        /* El Tokenizer generado por JFlex usa un Reader */
        Reader stringReader = new StringReader(code);
        tokenizer = new Tokenizer(stringReader);
        
        Token token;
        
        while(true){
            
            token = tokenizer.yylex();
            
            if(token == null){
                return;
            }
            
            tokenList.add(token);
        }
        
    }

    /**
     * Retorna la lista de tokens.
     **/
    public List<Token> getTokenList() {
        
        return tokenList;
    
    }
    
}
