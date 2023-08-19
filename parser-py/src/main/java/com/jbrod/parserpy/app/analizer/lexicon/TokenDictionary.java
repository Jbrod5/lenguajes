
package com.jbrod.parserpy.app.analizer.lexicon;

import java.util.HashMap;
import java.util.Map;

/**
 * Es un envoltorio para el diccionario de tokens
 * Permite separar mejor el codigo 
 * @author Jorge
 */
public class TokenDictionary {

    private Map<String, TokenTypeEnum> dictionary; 

    public TokenDictionary() {
    
    
        dictionary = new HashMap<>();
        
         //Aritmeticos
        dictionary.put("+"  , TokenTypeEnum.ARITHMETIC);
        dictionary.put("-"  , TokenTypeEnum.ARITHMETIC);
        dictionary.put("**" , TokenTypeEnum.ARITHMETIC);
        dictionary.put("/"  , TokenTypeEnum.ARITHMETIC);
        dictionary.put("//" , TokenTypeEnum.ARITHMETIC);
        dictionary.put("%"  , TokenTypeEnum.ARITHMETIC);
        dictionary.put("*"  , TokenTypeEnum.ARITHMETIC);
        
        //Comparacion
        dictionary.put("==" , TokenTypeEnum.LOGICAL);
        dictionary.put("!=" , TokenTypeEnum.LOGICAL);
        dictionary.put(">"  , TokenTypeEnum.LOGICAL);
        dictionary.put("<"  , TokenTypeEnum.LOGICAL);
        dictionary.put(">=" , TokenTypeEnum.LOGICAL);
        dictionary.put("<=" , TokenTypeEnum.LOGICAL);
        
        //Asignacion
        dictionary.put(  "=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put( "+=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put( "-=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put( "/=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put( "*=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put( "%=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put("**=" , TokenTypeEnum.ASSIGNAMENT);
        dictionary.put("//=" , TokenTypeEnum.ASSIGNAMENT);
        
        
            //verificar *= += -= 
        
        //Palabras clave
        dictionary.put("and"        , TokenTypeEnum.KEYWORD);
        dictionary.put("as"         , TokenTypeEnum.KEYWORD);
        dictionary.put("assert"     , TokenTypeEnum.KEYWORD);
        dictionary.put("break"      , TokenTypeEnum.KEYWORD);
        dictionary.put("class"      , TokenTypeEnum.KEYWORD);
        dictionary.put("continue"   , TokenTypeEnum.KEYWORD);
        dictionary.put("def"        , TokenTypeEnum.KEYWORD);
        dictionary.put("del"        , TokenTypeEnum.KEYWORD);
        dictionary.put("elif"       , TokenTypeEnum.KEYWORD);
        dictionary.put("else"       , TokenTypeEnum.KEYWORD);
        dictionary.put("except"     , TokenTypeEnum.KEYWORD);
        dictionary.put("False"      , TokenTypeEnum.KEYWORD);
        dictionary.put("finally"    , TokenTypeEnum.KEYWORD);
        dictionary.put("for"        , TokenTypeEnum.KEYWORD);
        dictionary.put("from"       , TokenTypeEnum.KEYWORD);
        dictionary.put("global"     , TokenTypeEnum.KEYWORD);
        dictionary.put("if"         , TokenTypeEnum.KEYWORD);
        dictionary.put("import"     , TokenTypeEnum.KEYWORD);
        dictionary.put("in"         , TokenTypeEnum.KEYWORD);
        dictionary.put("is"         , TokenTypeEnum.KEYWORD);
        dictionary.put("lambda"     , TokenTypeEnum.KEYWORD);
        dictionary.put("None"       , TokenTypeEnum.KEYWORD);
        dictionary.put("nonlocal"   , TokenTypeEnum.KEYWORD);
        dictionary.put("not"        , TokenTypeEnum.KEYWORD);
        dictionary.put("or"         , TokenTypeEnum.KEYWORD);
        dictionary.put("pass"       , TokenTypeEnum.KEYWORD);
        dictionary.put("raise"      , TokenTypeEnum.KEYWORD);
        dictionary.put("return"     , TokenTypeEnum.KEYWORD);
        dictionary.put("True"       , TokenTypeEnum.KEYWORD);
        dictionary.put("try"        , TokenTypeEnum.KEYWORD);
        dictionary.put("while"      , TokenTypeEnum.KEYWORD);
        dictionary.put("with"       , TokenTypeEnum.KEYWORD);
        dictionary.put("yield"      , TokenTypeEnum.KEYWORD);
        
        //Otros
        dictionary.put("(", TokenTypeEnum.OTHERS);
        dictionary.put(")", TokenTypeEnum.OTHERS);
        dictionary.put("[", TokenTypeEnum.OTHERS);
        dictionary.put("]", TokenTypeEnum.OTHERS);
        dictionary.put(",", TokenTypeEnum.OTHERS);
        dictionary.put(";", TokenTypeEnum.OTHERS);
        dictionary.put(":", TokenTypeEnum.OTHERS);
        
    }

    public Map<String, TokenTypeEnum> getDictionary() {
        return dictionary;
    }
    
    
}
