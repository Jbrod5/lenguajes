
package com.jbrod.analizer.parser.automatas;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class VariablesAsignacion {

    
    private Parser parser; 
    private LinkedList<SyntaxToken> syntaxList;  

    /**
     * Construye un objeto con una colección de autómatas para detectar variables y asignaciones.
     * @param TokenSyntaxList: Recibe la lista de tokens de sintaxis global de la clase maestra.
     **/
    public VariablesAsignacion(Parser parser, LinkedList<SyntaxToken> TokenSyntaxList) {
        this.parser = parser;
        this.syntaxList = TokenSyntaxList;
    }
    
    /*Estructura: identificador operador_asignacion expresión

    variable = identificador
    op asign  =   “ = ”  | “ += ” | “ -= ” |  “ **= ” | “ /= ” | “ //= ” | “ %= ” | “ *= ”

    asignación = identificador  { “ , ”  identificador }  op asign   expresión { “ , “ expresión }
    # comprobar  -> al menos la misma cantidad de expresiones que identificadores*/

    public boolean asignacionOrDeclaracion(List <Token> tokenList, int i){
        int inicio = i; 
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Asignacion";
        
        Token actual = tokenList.get(i);

        //Objetos necesarios para evaluar expresiones
        Expresiones expresiones = new Expresiones(parser, syntaxList); 
        ExpresionesCondicionales condicionales = new ExpresionesCondicionales(parser, syntaxList);

        int identificadores = 0; 

        // 1. Identificador
        if(actual.getTokenType() == Tokens.IDENTIFIER){

            // 1.1 Ver cuantos identificadores hay 
            identificadores = 1;
            sentencia += actual.getLexeme();
            i++;
            actual = tokenList.get(i);


            while(actual.getLexeme().equals(",")){
                sentencia += actual.getLexeme();
                i++;
                actual = tokenList.get(i);
                if(actual.getTokenType() == Tokens.IDENTIFIER){
                    identificadores++;
                    sentencia += actual.getLexeme();
                    i++;
                    actual = tokenList.get(i);
                }else{
                    //No puede quedar una coma que no esté acompañada de otro identificador
                    return false;
                }

            }

            if(actual.getTokenType() == Tokens.ASSIGNAMENT){
                sentencia +=  actual.getLexeme();
                i++;
                actual = tokenList.get(i);


                int exp = 0; 
                
                    if(expresiones.expresion(tokenList, i) || condicionales.condicional(tokenList, i)){

                    SyntaxToken ultimo = syntaxList.getLast();
                    sentencia += ultimo.getSentencia();
                    i = ultimo.getPosicionFinalLista() + 1;
                    actual = tokenList.get(i);
                            
                    exp++;
                    
                    
                    while(actual.getLexeme().equals(",")){
                        sentencia += actual.getLexeme();
                        i++;
                        actual = tokenList.get(i);
                        if(expresiones.expresion(tokenList, i) || condicionales.condicional(tokenList, i)){
                            ultimo = syntaxList.getLast();
                            sentencia += ultimo.getSentencia();
                            i = ultimo.getPosicionFinalLista()+1;
                            exp++;
                        }
                        
                        actual = tokenList.get(i);

                    }


                    if(identificadores == exp){
                        parser.agregarTokenSintactico(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                        parser.actualizarIterador(i);
                        return true; 
                    }

                }


            }else{
                // SOLO DECLARACION / ES
                tipo = "Declaracion";
                parser.agregarTokenSintactico(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                parser.actualizarIterador(i);
                return true;
            }

        }

        return false; 
    }
    
    
}
