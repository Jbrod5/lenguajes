package com.jbrod.analizer;

import com.jbrod.analizer.lexer.Lexer;
import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.parser.automatas.Recurrentes;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class Analizer{

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        
        
        
        System.out.println("Prueba rango: ");
        
        
        pruebaRange("range(20)");
        pruebaRange("range(20,2)");
        pruebaRange("range(2,3,4)");
        pruebaRange("x");
        System.out.println(""); System.out.println(""); System.out.println(""); System.out.println(""); 
        
        
        pruebaArreglo("[]");
        pruebaArreglo("[1]");
        pruebaArreglo("[2,3,4]");
        pruebaArreglo("[2,]");
        
    }
    
    private static void pruebaRange(String prueba) throws IOException{
        //Recurrentes r = new Recurrentes();
        Lexer lexer = new  Lexer();
        
        lexer.Analize(prueba);
        //System.out.println("Prueba rango: " + prueba + r.rango(lexer.getTokenList(), 0));
        
    }
    
    private static void pruebaArreglo(String prueba) throws IOException{
        //Recurrentes r = new Recurrentes();
        Lexer lexer = new Lexer(); 
        
        lexer.Analize(prueba);
        //System.out.println("Prueba Arreglo: " + prueba + r.array(lexer.getTokenList(), 0));
        
    }
}
