
package com.jbrod.parserpy.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Genera un archivo con una extension indicada y un path indicado(opcional).
 * @author Jorge
 */
public class FileGenerator {    
    private File file; 
    /**
     * @param toPrint: String con el contenido a almacenar en el archivo.
     * @param path: String con la ruta donde se guardara el archivo (puede ser nulo para guardar al nivel del jar).
     * @param extension: String con la extension que tendra el archivo (SIN PUNTO).
     * @param name: String con el nombre que tendra el archivo.
     **/
    public void generate(String toPrint, String path, String extension, String name){
        if(path == null){
            path = "";
        }
        
        path = path + name  + "." + extension;
        System.out.println("El fg debe guardar: " + path);
        
        try {
            file = new File(path);
            file.delete();
            
            if(!file.exists()){
                file.createNewFile();
            }else{ //reescribir el archivo 
                System.out.println("borrado: " + file.delete());
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file);
            fw.write("");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(toPrint);
            bw.close();
            fw.close();
            
            System.out.println("se imprimio: " +  path);
            
        } catch (Exception e) {
            
            //controlar excepcion
            System.out.println(e.getMessage());
            System.out.println(path);
            
        }
    }
}
