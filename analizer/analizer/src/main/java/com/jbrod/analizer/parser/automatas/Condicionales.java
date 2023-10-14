
package com.jbrod.analizer.parser.automatas;

import java.util.LinkedList;
import java.util.List;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.CodeBlock;
import com.jbrod.analizer.parser.tokens.SyntaxToken;

/**
 *
 * @author Jorge
 */
public class Condicionales {


    private Parser parser; 
    private LinkedList<SyntaxToken> syntaxTokens;

    public Condicionales(Parser parser, LinkedList<SyntaxToken> syntaxTokens){
        this.parser = parser; 
        this.syntaxTokens = syntaxTokens;
    }

    //CONDICIONALES: 
    //if =  “ if ”   expresión condicional   “ : “  bloque de código [ elif  { elif } ] [ else ]
    //elif  = “ elif ”  expresión condicional  “ : ”  bloque de código
    //else = “ else ”  bloque de código  -> En recurrentes (Sino)

    //# Un operador ternario es una expresión puesto que devuelve un valor 
    //# Una expresión condicional es una expresión puesto que devuelve un valor (v/f)


    public boolean condicional(List <Token> tokenList, int i){


        int inicio = i; 
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Condicional - ";

        Token actual = tokenList.get(i);
        
        //Objetos para evaluar expresiones condicionales / bloques de codigo
        ExpresionesCondicionales condicionales = new ExpresionesCondicionales(parser, syntaxTokens);
        CodeBlock codeBlock;
        Recurrentes r = new Recurrentes(syntaxTokens, parser);

        if(actual.getLexeme().equals("if") || actual.getLexeme().equals("elif")){                // "if"/elif
            tipo += actual.getLexeme();
            sentencia += actual.getLexeme();
            i++;
            actual = tokenList.get(i);

            if(condicionales.condicional(tokenList, i)){                                                           // expresion condicional
                SyntaxToken ultimo  = syntaxTokens.getLast();
                sentencia += ultimo.getSentencia();
                i = ultimo.getPosicionFinalLista() + 1;
                actual = tokenList.get(i);

                if(actual.getLexeme().equals(":")){                                                       //":"
                    sentencia += actual.getLexeme();
                    i++;
                    actual = tokenList.get(i);

                    // if contiene un bloque de codigo, puede continuar su ejecucion
                    codeBlock = new CodeBlock(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i);
                    parser.agregarTokenSintactico(codeBlock);
                    parser.actualizarIterador(i); 



                    //Retornar true
                    return true; 


                }
                
            }
 
        }

        return false;
    }
    
}
