
package com.jbrod.parserpy.app.analizer.lexicon;
import com.jbrod.parserpy.app.FileGenerator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 * Objeto que grafica un token con Graphviz.
 * @author Jorge
 */
public class TokenPlotter {
    
    /**
     * Manda a generar el grafico con Graphviz.\n
     * Crea una cadena con la estructura de codigo interpretable por Graphviz en base a las propiedades del token, luego 
     * la imprime con un FileGenerator en extension .dot para pasarla al graficador por medio de un ProcessBuilder.
     * 
     * @param token: Token que se quiere graficar.
     * @see FileGenerator.
     * @see Token.
     * @see ProcessBuilder.
     **/
    public void plot(Token token, String name){
        
        String label = token.getTokenType().toString(); //"Titulo" del grafo 
        
        //Definir el color del grafo
        String color = switch(label){
            case "ARITHMETIC", "COMPARISION","LOGICAL", "ASSIGNAMENT" -> "blue";
            case "KEYWORD"              -> "purple" ;
            case "CONSTANT"             -> "red"    ;
            case "COMMENT"              -> "grey"   ;
            case "OTHERS"               -> "green"  ; 
            case "IDENTIFIER"           -> "yellow"  ;
                
            //Caso especial, lexema desconocido    
            case "LEXICAL_ERROR_unknow_lexeme" -> "orange";
            default -> "orange";
                
        };
        
        //Define que es un *OPERADOR* en el titulo del grafo, junto al tipo de operador
        if(color.equals("blue")){
            label = "OPERADOR: " + label; 
        }
        
        //Agrega el lexema al titulo del grafo
        label += ": " + token.getLexeme();
        
        //Definiendo el estilo comun para los nodos
        String style = "[style = filled, color = " + color + ", label = ";
        
        //Definiendo cabecera del archivo .dot  ->  .dot es el archivo de entrada de Graphviz
        String dot = "digraph Plot{\n" 
                +   "   rankdir = LR;\n"
                +   "   label = " + "\"" + label+"\";\n";
        
        //Obteniendo el lexema a convertir en archivo .dot
        String lexema = token.getLexeme();
        
        //Declarando nodos con estilo: CADA PARTE DEL LEXEMA ES UN NODO
        for (int idx = 0; idx < lexema.length(); idx++) {
            
            String styleClose; 
            //Si es el ultimo nodo, es un estado de aceptacion
            if(idx == lexema.length()-1){
                styleClose = " ,shape = doublecircle];";
            }else{
                //De lo contrario, un nodo normal
                styleClose = "];";
            }
            //Agregando el nodo al .dot con su estilo y salto de linea
            dot += "    n" +idx + style + "\""+lexema.charAt(idx) +"\""+ styleClose + "\n";
            
        }
        
        //Conectando nodos
        for (int i = 0; i < lexema.length(); i++) {
            if((i == 0 && lexema.length() >=1) || i<lexema.length()-1 ){
                dot += "    n"+i+" -> n" +(i+1)+";\n"; 
            }
        }
        dot +="}";
    
        //-------------------------------- Generando archivo .dot -----------------------------------
        
        FileGenerator fg = new FileGenerator();
        
        fg.generate(dot, "Plots/", "dot", name.charAt(0) + "_toPlot");
        
       // -------------------------- Generando grafo con ProcessBuilder ----------------------------
       
        //Si ya hay una imagen creada, eliminarla para que no interfiera con el proceso
        File f = new File(name +".png");
        if(f.exists()){
            f.delete();
            System.out.println("Elimino la imagen?" + f.exists());
        }else{
            System.out.println("No elimino na " + f.exists());
        }
        
        //Crear el grafo
        try{
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", "./Plots/" + String.valueOf(name.charAt(0)) + "_" + name+".png", "./Plots/" + String.valueOf(name.charAt(0))+ "_toPlot.dot");
            pb.start();
            System.out.println(pb.command());
            pb.redirectErrorStream(true);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public void removeAllPlots(){
        /*
        ProcessBuilder pb = new ProcessBuilder("rmdir", "/s", "/q", "./Plots/");
        try {
            System.out.println(pb.command());
            pb.start();
        } catch (IOException ex) {
            Logger.getLogger(TokenPlotter.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        File file = new File("Plots/");
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException ex) {
            Logger.getLogger(TokenPlotter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        file.mkdir(); 
        
    }
    
}
