
package com.jbrod.parserpy.app.analizer.lexicon;

/**
 * Objeto que contiene los caracteres que pueden ser aceptados por un identificador y realiza su validacion.
 * REGEX: [ a-z | A-Z | _ ]+ [ a-z | A-Z | 0-9 | _ ]*
 * @author Jorge
 */
public class IdValidator {
    
    private static final char[] UPPERCASE_ALPHABET = new char[26];
    private static final char[] LOWERCASE_ALPHABET = new char[26];
    private static final char[] NUMBER_ALPHABET    = new char[10];
    private static final char   UNDERSCORE = '_';
    
    private IdAlphabetEnum alphabet; 
    
    public IdValidator() {
        
        UPPERCASE_ALPHABET[ 0] = 'A'; UPPERCASE_ALPHABET[ 7] = 'H'; UPPERCASE_ALPHABET[14] = 'O'; UPPERCASE_ALPHABET[20] = 'U';
        UPPERCASE_ALPHABET[ 1] = 'B'; UPPERCASE_ALPHABET[ 8] = 'I'; UPPERCASE_ALPHABET[15] = 'P'; UPPERCASE_ALPHABET[21] = 'V';
        UPPERCASE_ALPHABET[ 2] = 'C'; UPPERCASE_ALPHABET[ 9] = 'J'; UPPERCASE_ALPHABET[16] = 'Q'; UPPERCASE_ALPHABET[22] = 'W';
        UPPERCASE_ALPHABET[ 3] = 'D'; UPPERCASE_ALPHABET[10] = 'K'; UPPERCASE_ALPHABET[17] = 'R'; UPPERCASE_ALPHABET[23] = 'X';
        UPPERCASE_ALPHABET[ 4] = 'E'; UPPERCASE_ALPHABET[11] = 'L'; UPPERCASE_ALPHABET[18] = 'S'; UPPERCASE_ALPHABET[24] = 'Y';
        UPPERCASE_ALPHABET[ 5] = 'F'; UPPERCASE_ALPHABET[12] = 'M'; UPPERCASE_ALPHABET[19] = 'T'; UPPERCASE_ALPHABET[25] = 'Z';
        UPPERCASE_ALPHABET[ 6] = 'G'; UPPERCASE_ALPHABET[13] = 'N';
        
        LOWERCASE_ALPHABET[ 0] = 'a'; LOWERCASE_ALPHABET[ 7] = 'h'; LOWERCASE_ALPHABET[14] = 'o'; LOWERCASE_ALPHABET[20] = 'u';
        LOWERCASE_ALPHABET[ 1] = 'b'; LOWERCASE_ALPHABET[ 8] = 'i'; LOWERCASE_ALPHABET[15] = 'p'; LOWERCASE_ALPHABET[21] = 'v';
        LOWERCASE_ALPHABET[ 2] = 'c'; LOWERCASE_ALPHABET[ 9] = 'j'; LOWERCASE_ALPHABET[16] = 'q'; LOWERCASE_ALPHABET[22] = 'w'; 
        LOWERCASE_ALPHABET[ 3] = 'd'; LOWERCASE_ALPHABET[10] = 'k'; LOWERCASE_ALPHABET[17] = 'r'; LOWERCASE_ALPHABET[23] = 'x';
        LOWERCASE_ALPHABET[ 4] = 'e'; LOWERCASE_ALPHABET[11] = 'l'; LOWERCASE_ALPHABET[18] = 's'; LOWERCASE_ALPHABET[24] = 'y'; 
        LOWERCASE_ALPHABET[ 5] = 'f'; LOWERCASE_ALPHABET[12] = 'm'; LOWERCASE_ALPHABET[19] = 't'; LOWERCASE_ALPHABET[25] = 'z';
        LOWERCASE_ALPHABET[ 6] = 'g'; LOWERCASE_ALPHABET[13] = 'n';
        
        NUMBER_ALPHABET[ 0] = '0'; NUMBER_ALPHABET[ 3] = '3'; NUMBER_ALPHABET[ 6] = '6'; NUMBER_ALPHABET[ 8] = '8';
        NUMBER_ALPHABET[ 1] = '1'; NUMBER_ALPHABET[ 4] = '4'; NUMBER_ALPHABET[ 7] = '7'; NUMBER_ALPHABET[ 9] = '9';
        NUMBER_ALPHABET[ 2] = '2'; NUMBER_ALPHABET[ 5] = '5';
        
        
    }
    
    
    /**
     * Recibe una cadena que pertenece a un posible identificador, la valida y devuelve su resultado.
     * @param possibleIdentifier: cadena con un posible identificador.
     * @return validIdentifier: boolean.
     **/
    public boolean validIdentifier(String possibleIdentifier){
        boolean validIdentifier = true; //valida si un identificador es valido.
        boolean verified; //indica si un char ya fue analizado
        
        char in[] = possibleIdentifier.toCharArray(); 
        
        for (int i = 0; i < NUMBER_ALPHABET.length; i++) {
            if(in[0] == NUMBER_ALPHABET[i]){
                return false; 
            }
            
        }
        
        for (char c : in) {
            verified = false; 
            
            if(validIdentifier){
                
                if(!verified){
                    //primero recorrer los alfabetos de letras en busca de coincidencias
                    for (int i = 0; i < UPPERCASE_ALPHABET.length; i++) {
                        if(c == UPPERCASE_ALPHABET[i] || c == LOWERCASE_ALPHABET[i]){
                            verified = true; 
                            break;
                        }
                    }
                }
                
                if(!verified){
                    //verificar si es un numero
                    for (int i = 0; i < NUMBER_ALPHABET.length; i++) {
                        if(c == NUMBER_ALPHABET[i]){
                           verified = true;
                           break; 
                        }
                    }
                }
                
                if(!verified){
                    if(c == UNDERSCORE){
                        verified = true;
                        break;
                    }
                }
                
                validIdentifier = verified; 
                
                
            }else{
                
                break; 
                
            }
            
        }
        
        return validIdentifier; 
    }
}
