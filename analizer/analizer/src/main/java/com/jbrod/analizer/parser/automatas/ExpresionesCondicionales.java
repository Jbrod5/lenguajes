
package com.jbrod.analizer.parser.automatas;

import java.util.LinkedList;
import java.util.List;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.SyntaxToken;

/**
 *
 * @author Jorge
 */
public class ExpresionesCondicionales {
    
    //op log = “ and “ | “ or “ 
    //op neg = “ not ”  						# uso dif de “and” y “or” 
    //op comp = “ == “ | “ != ” | “ >= “  | “ <= “ | “ < ” | “ > “
    //expresión condicional = “ true “ | “ false “                  *
    //expresión condicional = [ op neg ]  expresión condicional     *
    //expresión condicional = expresión condicional       op log 	  expresión condicional -> tratarla dentro del automata que lo use
    //expresión condicional = ( expresión ) op comp ( expresión )   *
    //operador ternario = expresión  “if”  expresión condicional  “else” expresión *


    private Parser parser; 
    private LinkedList<SyntaxToken> syntaxList; 

    public ExpresionesCondicionales(Parser parser, LinkedList<SyntaxToken> syntaxList) {
        this.parser = parser;
        this.syntaxList = syntaxList;
    }
    
    


    /**
     * Evalúa una posible expresión condicional.
     * No actualiza el iterador del parser puesto que está planeado para uso desde otros autómatas.
     **/
    public boolean condicional(List <Token> tokenList, int i){

        int inicio = i; 
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Expresion condicional";

        Token actual = tokenList.get(i);
        Expresiones e = new Expresiones(parser, syntaxList);

        // 1. true/false
        if(actual.getLexeme().equals("True") || actual.getLexeme().equals("False")){
            //Es una expresion condicional
            sentencia += actual.getLexeme();
            syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i)); 
            return true;
            
            // 2. ( expresión ) op comp ( expresión )
        } else if(e.expresion(tokenList, i)){                   //(expresion)
            SyntaxToken ultimo = syntaxList.getLast();
            sentencia += ultimo.getSentencia();
            i = ultimo.getPosicionFinalLista() + 1;

            actual = tokenList.get(i);

            // expresion    op comp     expresion 
            if(actual.getTokenType() == Tokens.COMPARISION){    //op comp
                sentencia += actual.getLexeme();
                i++;
                actual = tokenList.get(i);

                if(e.expresion(tokenList, i)){                  //(expresion)
                    ultimo = syntaxList.getLast();
                    sentencia += ultimo.getSentencia();
                    i = ultimo.getPosicionFinalLista(); 
                    
                    syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                    return true; 
                }



                //expresión  “if”  expresión condicional  “else” expresión
            } else if(actual.getLexeme().equals("if")) {        //if
                sentencia += actual.getLexeme();
                i++;
                actual = tokenList.get(i);

                if(condicional(tokenList, i)){                          // expresion condicional
                    ultimo = syntaxList.getLast();
                    sentencia += ultimo.getSentencia();
                    i = ultimo.getPosicionFinalLista() + 1;
                    
                    actual = tokenList.get(i);

                    if(actual.getLexeme().equals("else")){      //else
                        sentencia += actual.getLexeme();
                        i++; 
                        actual = tokenList.get(i);

                        if(e.expresion(tokenList, i)){                  //expresion
                            ultimo = syntaxList.getLast();
                            sentencia += ultimo.getSentencia();
                            i = ultimo.getPosicionFinalLista() ;

                            syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                            return true; 
                        }
                    }
                }
            }

            // 3. [ op neg ]  expresión condicional
        } else if(actual.getLexeme().equals("not")){
            sentencia += actual.getLexeme();
            i++;

            if(condicional(tokenList, i)){
                SyntaxToken ultimo = syntaxList.getLast();
                sentencia += ultimo.getSentencia();
                i = ultimo.getPosicionFinalLista(); //No hago +1 pues aquí termina la ejecucion

                syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                return true; 
            }

        }  

        return false; 

    }


}
