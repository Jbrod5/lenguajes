
package com.jbrod.analizer.lexer;

/**
 *
 * @author Jorge
 */
public enum Tokens {
    Reservadas, Igual, Suma, Resta, Multiplicacion, Division, Identificador, Numero, ERROR,
    
    IDENTIFIER,
    ARITHMETIC, 
    COMPARISION, 
    LOGICAL, 
    ASSIGNAMENT,  
    KEYWORD, 
    CONSTANT, 
    COMMENT,
    OTHERS, 
    LEXICAL_ERROR_unknow_lexeme

}
