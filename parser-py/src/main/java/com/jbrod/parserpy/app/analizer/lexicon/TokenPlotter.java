
package com.jbrod.parserpy.app.analizer.lexicon;
import com.jbrod.parserpy.app.FileGenerator;
import java.io.File;

/**
 * Objeto que grafica un token con Graphviz.
 * @author Jorge
 */
public class TokenPlotter {
    
    public void plot(Token token){
        TokenTypeEnum tk; 
        String label = token.getTokenType().toString();
        
        String color = switch(label){
            //OPERADORES EN AZUL
            case "ARITHMETIC", "COMPARISION","LOGICAL", "ASSIGNAMENT" -> "blue";
            case "KEYWORD"              -> "purple" ;
            case "CONSTANT"            -> "red"    ;
            case "COMMENT"              -> "grey"   ;
            case "OTHERS"               -> "green"  ; 
            case "IDENTIFIER"           -> "yellow"  ;
                
            //Caso especial, lexema desconocido    
            case "LEXICAL_ERROR_unknow_lexeme" -> "orange";
            default -> "orange";
                
        };
        
        if(color.equals("blue")){
            label = "OPERADOR: " + label; 
        }
        label += ": " + token.getLexeme();
        
        String style = "[style = filled, color = " + color + ", label = ";
        
        
        String dot = "digraph Plot{\n" 
                +   "   rankdir = LR;\n"
                +   "   label = " + "\"" + label+"\";\n";
        
        String lexema = token.getLexeme();
        
        
        for (int idx = 0; idx < lexema.length(); idx++) {

            //declarar los nodos
            String styleClose; 
            if(idx == lexema.length()-1){
                styleClose = " ,shape = doublecircle];";
            }else{
                styleClose = "];";
            }
            dot += "    n" +idx + style + "\""+lexema.charAt(idx) +"\""+ styleClose + "\n";
            
        }
        
        for (int i = 0; i < lexema.length(); i++) {
            if((i == 0 && lexema.length() >=1) || i<lexema.length()-1 ){
                dot += "    n"+i+" -> n" +(i+1)+";\n"; 
            }
            
        }
        dot +="}";
        
        FileGenerator fg = new FileGenerator();
        fg.generate(dot, null, "dot", "toPlot");
        
        File f = new File("Plot.png");
        if(f.exists()){
            f.delete();
        }
        
        
        try{
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", "Plot.png", "./toPlot.dot");
            pb.start();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
}
