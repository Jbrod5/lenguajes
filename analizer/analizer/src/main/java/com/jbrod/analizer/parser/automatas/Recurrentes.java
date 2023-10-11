
package com.jbrod.analizer.parser.automatas;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.SyntaxToken;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase con autómatas recurrentes.
 * Retorna true o false 
 * - rango 
 * - arreglo
 * - diccionario
 * - par clave / valor
 * - tupla
 * - else 
 * - expresion condicional 
 * @author Jorge
 */
public class Recurrentes {

    
    /**
     * Analiza una lista de tokens en busca de expresiones recurrentes.
     * Estas expresiones son: 
     * - rango
     * - arreglo
     * - diccionario
     * - par clave / valor
     * - tupla
     * - else 
     * - expresion condicional 
     **/
    public SyntaxToken analizar(LinkedList<Token> tokenList, int inicio){
        
        
        
        return new SyntaxToken(); 
    }
    
    public boolean rango(List<Token> tokenList, int i){
        Token actual;
        actual = tokenList.get(i);
        
        //rango = “ range “     “ ( “   dígito   [  “ , ”   digito ]   [ “ ,“  digito ]    “ ) “ --------------------------
        if(actual.getLexeme().equals("range")){
            i ++;
            actual = tokenList.get(i);
            
            if(actual.getLexeme().equals("(")){
                i++;
                actual = tokenList.get(i);
                
                if(actual.getTokenType() == Tokens.INTEGER){
                    i++;
                    actual = tokenList.get(i);
                    
                    if(actual.getLexeme().equals(")")){
                    
                        // Es un rango de un solo argumento, retornar el token
                        return true; 
                        
                    } else if( actual.getLexeme().equals(",")){
                        i++; 
                        actual = tokenList.get(i);
                        
                        if(actual.getTokenType() == Tokens.INTEGER){
                            i++;
                            actual = tokenList.get(i);
                            
                            if(actual.getLexeme().equals(")")){
                            
                                // Es un rango de dos argumentos, retornar el token
                                return true; 
                                
                            }else if( actual.getLexeme().equals(",") ){
                                i++;
                                actual = tokenList.get(i);
                                
                                if(actual.getTokenType() == Tokens.INTEGER){
                                    i++;
                                    actual = tokenList.get(i);
                                    
                                    if(actual.getLexeme().equals(")")){
                                        
                                        // Es un rango de tres argumentos, retornar el token
                                        return true; 
                                    }
                                }
                            }
                        }
                        
                    }
                    
                }
                
            }
        }
        
        return false; 
        
    }
    
    public boolean array(List<Token> tokenList, int i){
    
        Token actual = tokenList.get(i);
        
        //arreglo arreglo = “[“   constante | variable  “]” 			 
        if(actual.getLexeme().equals("[") ){
            int checkpoint = i ;
            
            i++;
            actual = tokenList.get(i);
            
            while(! actual.getLexeme().equals("]")){
                
                if(actual.getTokenType() == Tokens.CONSTANT || actual.getTokenType() == Tokens.INTEGER  
                                                /* || actual.getTokenType() == Tokens.IDENTIFIER */) {
                    
                    i++;
                    actual = tokenList.get(i);
                    if( actual.getLexeme().equals(",")){
                        i++;
                        actual = tokenList.get(i);
                         
                    }else if(actual.getLexeme().equals("]")){
                        break; 
                    }
            
                }
                
            }
            
            if(actual.getLexeme().equals("]")){
                //Es un arreglo, retornar el token
                return true; 
            }
        }
        
        return false; 
    }
    
}
