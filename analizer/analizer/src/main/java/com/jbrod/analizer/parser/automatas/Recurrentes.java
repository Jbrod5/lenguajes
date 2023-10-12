
package com.jbrod.analizer.parser.automatas;

import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.CodeBlock;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
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

    private Parser parser; 
    
    
    private LinkedList <SyntaxToken> TokenSyntaxList; 

    /**
     * Construye un objeto con una coleccion de automatas para detectar sentencias recurrentes.
     * @param TokenSyntaxList: Recibe la lista de tokens de sintaxis global de la clase maestra. 
     **/
    public Recurrentes(LinkedList<SyntaxToken> TokenSyntaxList, Parser parser) {
        this.TokenSyntaxList = TokenSyntaxList;
        this.parser = parser; 
    }    
    
    
    
    /**
     * Analiza una lista de tokens en busca de expresiones recurrentes.
     * Estas expresiones son: 
     * - rango -
     * - arreglo -
     * - diccionario - 
     * - par clave / valor -
     * - tupla -
     * - else 
     * - expresion condicional 
     **/
    //public SyntaxToken analizar(LinkedList<Token> tokenList, int inicio){
        
        
        
        //return new SyntaxToken(); 
    //}
    
    public boolean rango(List<Token> tokenList, int i){
        int checkpoint = i; 
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Rango";
        
        Token actual;
        actual = tokenList.get(i);
        
        //rango = “ range “     “ ( “   dígito   [  “ , ”   digito ]   [ “ ,“  digito ]    “ ) “ --------------------------
        if(actual.getLexeme().equals("range")){
            sentencia += actual.getLexeme();
            i ++;
            actual = tokenList.get(i);
            
            if(actual.getLexeme().equals("(")){
                sentencia += actual.getLexeme();
                i++;
                actual = tokenList.get(i);
                
                if(actual.getTokenType() == Tokens.INTEGER){
                    sentencia += actual.getLexeme();
                    i++;
                    actual = tokenList.get(i);
                    
                    if(actual.getLexeme().equals(")")){
                    
                        sentencia += actual.getLexeme();
                        // Es un rango de un solo argumento, retornar el token
                        parser.agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                        parser.actualizarIterador(i + 1); //Se hace i + 1 para no caer en el final del reconocimiento
                        return true; 
                        
                    } else if( actual.getLexeme().equals(",")){
                        sentencia += actual.getLexeme();
                        i++; 
                        actual = tokenList.get(i);
                        
                        if(actual.getTokenType() == Tokens.INTEGER){
                            sentencia += actual.getLexeme();
                            i++;
                            actual = tokenList.get(i);
                            
                            if(actual.getLexeme().equals(")")){
                            
                                sentencia +=actual.getLexeme();
                                // Es un rango de dos argumentos, retornar el token
                                parser.agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                                parser.actualizarIterador(i+1);
                                return true; 
                                
                            }else if( actual.getLexeme().equals(",") ){
                                sentencia += actual.getLexeme();
                                i++;
                                actual = tokenList.get(i);
                                
                                if(actual.getTokenType() == Tokens.INTEGER){
                                    sentencia += actual.getLexeme();
                                    i++;
                                    actual = tokenList.get(i);
                                    
                                    if(actual.getLexeme().equals(")")){
                                        
                                        // Es un rango de tres argumentos, retornar el token
                                        sentencia += actual.getLexeme();
                                        parser.agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                                        parser.actualizarIterador(i+1);
                                        return true; 
                                    }
                                }
                            }
                        }       
                    }   
                } 
            }
        }
        
        parser.actualizarIterador(checkpoint);
        return false; 
        
    }
    
    public boolean array(List<Token> tokenList, int i){
        int backup = i; 
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Array";
        
        Token actual;
        actual = tokenList.get(i);
        
        //arreglo arreglo = “[“   constante | variable  “]” 			 
        if(actual.getLexeme().equals("[") ){
            
            int checkpoint = i ;
            
            sentencia += actual.getLexeme();
            i++;
            actual = tokenList.get(i);
            
            while(! actual.getLexeme().equals("]")){
                
                if(actual.getTokenType() == Tokens.CONSTANT || actual.getTokenType() == Tokens.INTEGER || actual.getTokenType() == Tokens.IDENTIFIER ) {
                    sentencia += actual.getLexeme();
                    i++;
                    actual = tokenList.get(i);
                    if( actual.getLexeme().equals(",")){
                        sentencia += actual.getLexeme();
                        i++;
                        actual = tokenList.get(i);
                    }else if(actual.getLexeme().equals("]")){
                        break; 
                    }else{
                        parser.actualizarIterador(backup);
                        return false; 
                    }
                }
            }
            
            if(actual.getLexeme().equals("]")){
                //Es un arreglo, retornar el token
                sentencia += actual.getLexeme();
                parser.agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                parser.actualizarIterador(i+1);
                return true; 
            }
        }
        parser.actualizarIterador(backup);
        return false; 
    }
    
    public boolean diccionario(List<Token>  tokenList, int i){
        int checkpoint = i; 
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Diccionario";
        
        Token actual = tokenList.get(i);
        
        //diccionario = “{”   [ par clave valor ]   “}”			# puede estar vacío
        if(actual.getLexeme().equals("{")){
            sentencia += actual.getLexeme();
            i++;
            actual = tokenList.get(i);
            
            while(!actual.getLexeme().equals("}")){
                if(parClaveValor(tokenList, i)){
                   SyntaxToken tkn = TokenSyntaxList.getLast();
                   //posicion final mas uno, de lo contrario, evaluaremos el final del token guardado
                   i = tkn.getPosicionFinalLista() + 1;
                   actual = tokenList.get(i);
                   sentencia += tkn.getSentencia();
                   
                   if(actual.getLexeme().equals(",")){
                       sentencia += actual.getLexeme();
                       i++;
                       actual = tokenList.get(i);
                       
                   }else{
                       break; 
                   }
                }
            }    
            
            if( actual . getLexeme().equals("}")){
                    //Es un diccionario, agregar el token
                    sentencia+= actual.getLexeme();
                    parser.agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                    parser.actualizarIterador(i+1);
                    return true;
            }
            
        }
        parser.actualizarIterador(checkpoint);
        return false; 
    }
    
    public boolean parClaveValor(List<Token> tokenList, int i){
        //int checkpoint = i;
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Par clave - valor";
        
        Token actual = tokenList.get(i);
        
        //par clave valor = identificador “:” constante			# constante es terminal
        if(actual.getTokenType() == Tokens.IDENTIFIER){
            sentencia += actual.getLexeme();
            i++; 
            actual = tokenList.get(i);
            
            if(actual.getLexeme().equals(":")){
                sentencia += actual.getLexeme();
                i++; 
                actual = tokenList.get(i);
                
                if(actual.getTokenType() == Tokens.CONSTANT || actual.getTokenType() == Tokens.IDENTIFIER || actual.getTokenType() == Tokens.INTEGER){
                    sentencia += actual.getLexeme();
                    TokenSyntaxList. add( new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                    //parser.actualizarIterador(i+1);
                    return true;
                }
            }
        }
        //parser.actualizarIterador(checkpoint);
        return false; 
    }
    
    public boolean tupla(List<Token> tokenList, int i){
        int checkpoint = i;
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Tupla";
        
        Token actual = tokenList.get(i);
        
        //tupla = “(“   {  constante  }  “)”					# son inmutables
        
        if(actual.getLexeme().equals("(")){
        
            sentencia += actual.getLexeme();
            i++;
            actual = tokenList.get(i);
            
            while (! actual.getLexeme().equals(")")){
                
                if(actual.getTokenType() == Tokens.CONSTANT || actual.getTokenType() == Tokens.INTEGER){
                    sentencia += actual.getLexeme();
                    i++; 
                    actual = tokenList.get(i);
                    
                    if(actual.getLexeme().equals(",")){
                        sentencia += actual.getLexeme();
                        i++; 
                        actual = tokenList.get(i);
                    }else{
                        break;
                    }
                }
                
            }
            
            if(actual.getLexeme().equals(")")){
                //Es una tupla, añadir el token
                sentencia += actual.getLexeme();
                parser. agregarTokenSintactico(new SyntaxToken( sentencia ,inicio.getRow(), inicio.getColumn(), tipo, i) );
                parser.actualizarIterador(i+1);
                return true; 
            }
        }
        parser.actualizarIterador(checkpoint);
        return false; 
    }
    
    
    
    
    
    
    //else
    public boolean sino(List<Token> tokenList, int i){
        
        Token inicio = tokenList.get(i);
        String sentencia = ""; 
        String tipo = "Else";
        
        Token actual = tokenList.get(i);
        
        if(actual.getLexeme().equals("else")){
            sentencia += actual.getLexeme();
            i++;
            

            //else contiene un bloque de codigo, puede continuar su ejecucion
            CodeBlock codeBlock = new CodeBlock(sentencia, actual.getRow(), actual.getColumn(), tipo, i);
            parser.agregarTokenSintactico(codeBlock);
            parser.actualizarIterador(i);
            return true; 
            
            //llamar a reconocimiento de bloque de codigo, TERMINA CUANDO ENCUENTRE UN TOKEN EN UNA COLUMNA MENOR QUE EL ELSE
              //int columnaInicial = actual.getColumn();
              //if(codeblock(columnaInicial, tokenList, i)){
             
              //}            
        }
        
        return false; 
    }
    
    
    
    
    
    
    
    
    //RECONOCER BLOQUES DE CODIGO -> quiza debe ser el inicial
    /**
     * Reconoce bloques de codigo, termina cuando encuentra un token en una columna menor que Columna Inicial
     * @param columnaInicial: identación que deben tener las sentencias para pertenecer al bloque de codigo.
     **/
    /*public boolean codeblock(int columnaInicial, List<Token> tokenList, int i){
    
        parser.Parse((LinkedList<Token>) tokenList);
        
        
        
       return false;
       
    }*/
}
