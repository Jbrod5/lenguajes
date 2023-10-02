
package com.jbrod.analizer.lexer;

/**
 *
 * @author Jorge
 */
public enum Tokens {
    
    IDENTIFIER,
    ARITHMETIC, 
    COMPARISION, 
    LOGICAL, 
    ASSIGNAMENT,  
    KEYWORD, 
    CONSTANT, 
    COMMENT,
    OTHERS, 
    
    EOL, //fin de linea
    LEXICAL_ERROR_unknow_lexeme

}
