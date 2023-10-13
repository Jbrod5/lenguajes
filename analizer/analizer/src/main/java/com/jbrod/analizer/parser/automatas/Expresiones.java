
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
public class Expresiones {
    
    //expresión = variable  -> identificador				# variable declarada?    -
    //expresión = función   -> identificador + tupla			# void es una función    -
    
    //expresión = arreglo { arreglo }                                   # arreglo n- dimensional -
    //expresión = diccionario  -
    //expresión = constante -

    private Parser parser; 
    private LinkedList<SyntaxToken> syntaxList; 
    
    /**
     * Evalúa una posible expresión.
     * No actualiza el iterador del parser puesto que no está planeado para usarse desde allí, sino desde otros autómatas que requieran verificar expresiones.
     **/
    public boolean expresion(List <Token> tokenList, int i){
        int inicio = i;
        Token inicial = tokenList.get(i);
        String sentencia = "";
        String tipo = "Expresion";
        
        Token actual = tokenList.get(i);
        Recurrentes r = new Recurrentes(syntaxList, parser);
        
        //1. Variable / Funcion
        if(actual.getTokenType() == Tokens.IDENTIFIER){
            
            if(r.tupla(tokenList, i+1)){
                //FUNCION: ID + TUPLA
                tipo += " | Llamado a función";
                sentencia += actual.getLexeme(); 
                sentencia += syntaxList.getLast().getSentencia();
                syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                return true;
            }else{
                //VARIABLE
                tipo += " | Variable";
                sentencia += actual.getLexeme(); 
                syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
                return true;
            } 
            
            //Constante
        } else if(actual.getTokenType() == Tokens.CONSTANT || actual.getTokenType() == Tokens.INTEGER){
            sentencia += actual.getLexeme();
            tipo += " | Constante";
            syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(),inicial.getColumn(), tipo, i));
            return true; 
            
            //Arreglo /n - dimensional
        }else if(r.array(tokenList, i)){
            tipo += " | Arreglo";
            sentencia += syntaxList.getLast().getSentencia();
            i++;
            boolean ndimensional = false; 
            while(r.array(tokenList, i)){
                ndimensional = true;
                //recuperar la posicion final y agregar uno
                SyntaxToken tkn = syntaxList.getLast();
                sentencia += tkn.getSentencia();
                i = tkn.getPosicionFinalLista() + 1;
            }
            if(ndimensional){ i--; }
            syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
            return true; 
            
            // Diccionario
        } else if(r.diccionario(tokenList, i)){
            tipo += " | Diccionario";
            sentencia += syntaxList.getLast().getSentencia();
            syntaxList.add(new SyntaxToken(sentencia, inicial.getRow(), inicial.getColumn(), tipo, i));
            return true;
        }
        
        return false; 
    }
    
    
}
