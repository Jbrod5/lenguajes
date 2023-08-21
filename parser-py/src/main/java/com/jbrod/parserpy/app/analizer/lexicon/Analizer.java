
package com.jbrod.parserpy.app.analizer.lexicon;

import com.jbrod.parserpy.app.FileGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge
 */
public class Analizer {

    //Diccionario: clave - valor | si recibe una cadena repetida reescribe la entrada anterior
    private Map<String, TokenTypeEnum> dictionary; 
    private TokenDictionary td; 
    private List<Token> listToken; 
    
    private IdValidator idValidator; 
    private NumberValidator numValidator;  
    
    private String report; 

    public Analizer() {
        
        td = new TokenDictionary();
        dictionary = td.getDictionary();
        listToken = new ArrayList<>();
        
        idValidator = new IdValidator(); 
        numValidator = new NumberValidator(); 
        
    }
    
    /**
     * Lee la cadena de entrada caracter por caracter y determina coincidencias con el TokenDictionary.
     * @param textInput: Cadena que contiene todo el codigo a analizar.
     * @see TokenDictionary
     * @see TokenTypeEnum
     * @see Token
     **/
    public void searchMatches(String textInput){

        listToken.clear();
        textInput += " \n\r";
        char[] input = textInput.toCharArray();
        int rows = 0; 
        int columns = 1;
        String buffer = ""; 
        
        int tokenColumn = -1; //-> columna donde se ecuentra el inicio de un token, -1 indica que no está reconociendo NINGUN token en ese momento 
        
        //cuando detecta comillas permite TODO hasta las siguientes comillas
        boolean text = false; 
        boolean comment = false; 
        
        
        for (char c : input) {
            System.out.println(columns + ": "+ c);
            
            switch(c){
                
                case ' ': //Se reinicia el buffer y el identificador columna de inicio de token, luego compara la cadena 
                    if(!text && !comment){
                        createToken(buffer, rows, tokenColumn, text);
                        buffer = "";
                        tokenColumn = -1; 
                    }else{
                        buffer = buffer + c; 
                    }
                    break;
                    
                    
                case '\"', '\'': //Para este analizador es indiferente comillas simples o dobles
                    if(!comment && text){
                        //llamar a crear token COMO CADENA (constante ), reiniciar buffer
                        createToken(buffer, rows, tokenColumn, text);
                        buffer = "";
                    }else{
                        //empieza a reconocer una cadena de texto
                        if(!comment){
                            tokenColumn = columns;
                        }
                         
                    }
                    text = !text; //cambia de estado el boolean texto
                    break; 
                     
                    
                case '\n', '\r':
                    if(!text){
                        columns = 1;
                        if(comment){
                            createToken(buffer, rows, tokenColumn, text);
                            //tokenColumn = -1;
                            comment = false;
                        }
                         
                    }else{  
                        buffer = buffer + c; 
                    }
                    rows ++;
                    break; 
                    
                case '#':
                    buffer = buffer + c;                    
                    comment = true; 
                    tokenColumn = columns; 
                    break; 
                    
                default:
                    buffer = buffer + c;
                    if(tokenColumn == -1){
                        tokenColumn = columns;                        
                    }
                    break; 
                    
                
            }
            
            columns ++;
        }
        
    }
    
    
    /**
     * Crea un nuevo Token y lo añade al ArrayList.
     **/
    private void createToken(String buffer, int row, int column, boolean text){
        
        String tokenType = ""; 
        String pattern = "";
        
        if(!buffer.isBlank()){
            
            if(dictionary.containsKey(buffer)){ //token contenido en el diccionario
                
                tokenType = dictionary.get(buffer).toString();
                pattern = buffer; 

            } else if(text){ //constante de texto
                
                tokenType = TokenTypeEnum.CONSTANT.toString();
                buffer = "\"" + buffer +"\"";
                pattern = "[a-z|A-Z|0-9|]+";
                
            }else if(idValidator.validIdentifier(buffer)){
                
                tokenType = TokenTypeEnum.IDENTIFIER.toString();
                pattern = "[ a-z | A-Z | _ ]+ [ a-z | A-Z | 0-9 | _ ]*";
                
            }else if(buffer.charAt(0) == '#'){
                tokenType = TokenTypeEnum.COMMENT.toString(); 
                pattern = "# [.*] \\n";
                
            }else if(numValidator.validNumber(buffer)){
                tokenType = TokenTypeEnum.CONSTANT.toString(); 
                pattern = "[0 - 9]+  | ( [ 0 - 9]+ . [ 0 - 9] +  | [ 0 - 9]+ . [ 0 - 9] + f)";
            }else{
                
                //NO RECONOCIDO
                tokenType = TokenTypeEnum.LEXICAL_ERROR_unknow_lexeme.toString(); 
                pattern = buffer;
                
            }

            Token token = new Token(tokenType, pattern, buffer, row, column);
            listToken.add(token);
            
        }
        
    }

    /**
     * Genera el reporte del analisis en un archivo de tipo csv.\n
     * Utiliza el modelo proporcionado en el documento de la prácita.\n
     * Token | Patron | Lexema | Linea | Columna
     * @see Token.
     **/
    public void generateAnalysisReport(){
        report = null;
        
        report = "Token,Patron,Lexema,Linea,Columna";
        
        //recorre la lista de Tokens
        Token actual;
        for (int i = 0; i < listToken.size(); i++) {
           actual = listToken.get(i);
           String tktp, ptrn, lxm,rw,clmn;
           
           tktp     = actual.getTokenType               () + ",";
           ptrn     = actual.getPattern                 () + ","; 
           lxm      = actual.getLexeme                  () + ","; 
           rw       = String.valueOf(actual.getRow    ())+ ","; 
           clmn     = String.valueOf(actual.getColumn ())+ ",";
           
           ptrn.replaceAll("(\n|\r)", "");
           lxm.replaceAll("(\n|\r)", "");
           
           if(actual.getPattern().equals(",")){
               ptrn     = "-comma-,";
               lxm      = "-comma-,";
           }
           
           report += "\n" + tktp + ptrn + lxm + rw + clmn;
        }
        
        //mandar a crear el csv
        FileGenerator fg = new FileGenerator(); 
        fg.generate(report, null, "csv", "report");
        
    }
    
    public List<Token> getListToken() {
        return listToken;
    }

    public String getReport() {
        return report;
    }
    
}
