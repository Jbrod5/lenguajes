
package com.jbrod.analizer.parser.automatas;

import java.util.LinkedList;
import java.util.List;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.Funcion;
import com.jbrod.analizer.parser.tokens.SyntaxToken;

/**
 *
 * @author Jorge
 */
public class Funciones {

    
    private Parser parser; 
    private LinkedList<SyntaxToken> syntaxList; 
    
    public Funciones(Parser parser, LinkedList<SyntaxToken> syntaxList){
        this.parser = parser; 
        this.syntaxList = syntaxList;
    }
    

    //función = “def”  identificador  “ ( ”  [ identificador {  “ , ”  identificador } ]  “  ) ”   bloque de código [ return  expresión  ]
    
    public boolean funcion(List <Token> tokenList, int i ){

        int inicio = i; 
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Funcion";

        Token actual = tokenList.get(i);

        //1. "def"
        if(actual.getLexeme().equals("def")){
            sentencia += actual.getLexeme();
            i++;
            if(i<tokenList.size()){
                actual = tokenList.get(i);
            }

            // 2. identificador
            if(actual.getTokenType() == Tokens.IDENTIFIER){
                sentencia += actual.getLexeme();
                i++;
                if(i<tokenList.size()){
                    actual = tokenList.get(i);
                }

                //3. tupla
                Recurrentes r = new Recurrentes(syntaxList, parser); 
                if(r.tupla(tokenList, i)){
                    SyntaxToken ultimo = syntaxList.getLast();
                    sentencia += ultimo.getSentencia();
                    i = ultimo.getPosicionFinalLista();

                    String[] params = ultimo.getSentencia().split(",");
                    LinkedList<String> parm = new LinkedList<>();
                    if(params.length > 2){
                        for (int j = 1; j < params.length-1; j++) {
                            parm.add(params[j]);
                        }
                    }
                    
                    Funcion funcion = new Funcion(parm, sentencia, inicial.getRow(), inicial.getColumn(), tipo, i);
                    parser.agregarTokenSintactico(funcion);
                    parser.actualizarIterador(i+1);
                    return true; 
                }
            }
        }

        return false; 
    }

}
