
package com.jbrod.parserpy.app.analizer.lexicon;

/**
 *
 * @author Jorge
 */
public class NumberValidator {

    /**
     * Recibe una cadena que pertenecea a un posible numero, la valida y devuelve su resultado.
     * @param possibleNumber: cadena con un posible numero.
     * @return validIdentifier: boolean.
     **/
    public boolean validNumber(String possibleNumber){
        
        boolean validNumber = true; 
        
        boolean integer     = false; 
        boolean doublenum   = false;
        boolean floatnum    = false; 
        
        try {
            int validInt = Integer.parseInt(possibleNumber);
            integer = true; 
        } catch (Exception e) {
            integer = false; 
        }
        
        try {
            double validDob = Double.parseDouble(possibleNumber);
            doublenum = true; 
        } catch (Exception e) {
            doublenum = false; 
        }
        
        try {
            float validFloat = Float.parseFloat(possibleNumber);
            floatnum = true; 
        } catch (Exception e) {
            floatnum = false; 
        }
        
        return integer | doublenum | floatnum; 
    }
    
}
