
package com.jbrod.parserpy.app.analizer.lexicon;

/**
 *
 * @author Jorge
 */
public class PatternIdentifier {

    public String identify(String tokenType, String buffer){
        String pattern = "";
        
        switch (tokenType) {
            case "ARITHMETIC": 
                pattern = "[0-9]+";
                break; 
            case "COMPARISION": 
                pattern = buffer; 
                break; 
        }
        
        return pattern; 
    }
}
