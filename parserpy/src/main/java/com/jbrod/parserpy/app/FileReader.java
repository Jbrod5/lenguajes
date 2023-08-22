
package com.jbrod.parserpy.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Lee un arcivo con un path especificado y luego devuelve el texto contenido en el.
 * @author jorge
 */
public class FileReader {
    
    
    
    /**
     * Lee un archivo con un path especificado y luego devuelve el texto contenido en el.
     * @param path: ruta del archivo que se desea leer.
     **/
    public String read(String path){
        String content = ""; 
        
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                content += scanner.nextLine(); 
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return content; 
    }
    
}
