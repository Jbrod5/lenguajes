
package com.jbrod.analizer.parser.automatas;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.CodeBlock;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Ciclos {

    private Parser parser; 
    private LinkedList <SyntaxToken> syntaxTokens; 
    
    public Ciclos(Parser parser, LinkedList <SyntaxToken> syntaxTokens){
        this.parser = parser; 
        this.syntaxTokens = syntaxTokens;
    }
     
    // FOR - WHILE
    // for = “ for ”  identificador  “ in “  secuencia  bloque de código [ else ]
    // secuencia  =  arreglo | rango | caddoblecomilla | cadsimplecomilla | diccionario | tupla

    // while =  “ while”    expresión condicional    “ : ”    bloque de código


    public boolean ciclo(List <Token> tokenList, int i){

        int inicio = i; 
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Ciclo : ";

        Token actual = tokenList.get(i);
        Recurrentes recurrentes = new Recurrentes(syntaxTokens, parser);
        
        if(actual.getLexeme().equals("for")){                                       //"for"

            tipo += actual.getLexeme();
            sentencia += actual.getLexeme(); 
            i++;
            actual = tokenList.get(i);

            if(actual.getTokenType() == Tokens.IDENTIFIER){                                 //identificador

                sentencia += actual.getLexeme(); 
                i++;
                actual = tokenList.get(i);

                if(actual.getLexeme().equals("in")){                              //"in"

                    sentencia += actual.getLexeme();
                    i++;
                    actual = tokenList.get(i);

                    
                    // secuencia  =  arreglo | rango | caddoblecomilla | cadsimplecomilla | diccionario | tupla
                    Tokens type = actual.getTokenType();        //secuencia
                    if(type == Tokens.TEXT || recurrentes.array(tokenList, i) || recurrentes.rango(tokenList, i) || recurrentes.diccionario(tokenList, i) || recurrentes.tupla(tokenList, inicio)){

                        if(type != Tokens.TEXT){
                            SyntaxToken ultimo = syntaxTokens.getLast();
                            i = ultimo.getPosicionFinalLista();
                            sentencia += ultimo.getSentencia();

                        }else{
                            sentencia += actual.getLexeme();
                        }
                                                                                        
                        CodeBlock nuevo = new CodeBlock(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i);
                        parser.agregarTokenSintactico(nuevo);
                        parser.actualizarIterador(i+1);                                     // codeblock
                        return true;
                    }                   

                }

            }


        // while =  “ while”    expresión condicional    “ : ”    bloque de código
        }else if(actual.getLexeme().equals("while")){                                                        // "while"

            tipo += actual.getLexeme();
            sentencia += actual.getLexeme(); 
            i++;
            actual = tokenList.get(i);
    
            ExpresionesCondicionales condicionales = new ExpresionesCondicionales(parser, syntaxTokens);            //expresion condicional 
            if(condicionales.condicional(tokenList, i)){
                SyntaxToken ultimo = syntaxTokens.getLast();
                sentencia += ultimo.getSentencia();
                i = ultimo.getPosicionFinalLista() + 1; 
                actual = tokenList.get(i);

                if(actual.getLexeme().equals(":")){                                                         // " : "

                    CodeBlock nuevo = new CodeBlock(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i);
                    parser.agregarTokenSintactico(nuevo);
                    parser.actualizarIterador(i+1);                                                                  // codeblock
                    return true;

                }
            }


        }


        return false;

    }
}
