package com.jbrod.analizer;

import com.jbrod.analizer.lexer.Lexer;
import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.automatas.Recurrentes;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class Analizer{

    public static void main(String[] args) throws IOException {
        /*System.out.println("Hello World!");
        System.out.println("Prueba rango: ");
        pruebaRange("range(20)");
        pruebaRange("range(20,2)");
        pruebaRange("range(2,3,4)");
        pruebaRange("x");
        System.out.println(""); System.out.println(""); System.out.println(""); System.out.println(""); 
        
        
        pruebaArreglo("[]");
        pruebaArreglo("[1]");
        pruebaArreglo("[2,3,4]");
        pruebaArreglo("[2,]");*/
        
        
        String prueba =

                "x = 4"
                + "if a>b"
                + "while a>b"
                + "a+=1"
                + "\narreglo2 = [{id:1, nombre:\"Sin nombre\"}, {id:2, nombre:\"Xalarga\"}]\n" +
"dicc = {}\n" +
"\n" +
"# condicional simple\n" +
"if True:\n" +
"    # Bloque de código\n" +
"    miVariable = \"Verdadero\"\n" +
"else:\n" +
"    miVariable = \"Falso\"\n" +
"\n" +
"# condicional anidado\n" +
"a = 5\n" +
"v = 8\n" +
"if a > v:\n" +
"    # Bloque de código\n" +
"    resultado = \"a es mayor que v\"\n" +
"elif a < v:\n" +
"    # Bloque de código\n" +
"    resultado = \"a es menor que v\"\n" +
"else:\n" +
"    resultado = \"a es igual a v\"\n" +
"\n" +
"# ciclos\n" +
"for i in range(5):\n" +
"    print(i)\n" +
"\n" +
"contador = 0\n" +
"while contador < 5:\n" +
"    print(contador)\n" +
"    contador += 1\n" +
"\n" +
"\n" +
"#funciones\n" +
"def suma(a, b):\n" +
"    return a + b\n" +
"\n" +
"resultado = suma(3, 5)\n" +
"\n" +
"\n" +
"def funcion_compleja(a, b):\n" +
"    if a > b:\n" +
"        for i in range(b, a):\n" +
"            print(i)\n" +
"    else:\n" +
"        while a < b:\n" +
"            print(a)\n" +
"            a += 1\n" +
"    return a\n" +
"\n" +
"resultado_final = funcion_compleja(3, 8)"
                + ""
                /*+ "a,b,c = 4,3,2"
                + "\nrange (2) (2,4,6) "
                + "\n [] [5,2,5] else range (4,2,3)"
                + "\n {frifayer : 4, pedrigri : \" piedro \"}"
                + "\n ('chamba', 4)\n"
                + "else range(4,3,2) esatunolatiene []" */;
        
        
        System.out.println("A analizar: ");
        System.out.println(prueba); System.out.println("");
        
        Lexer lexer = new Lexer(); 
        lexer.Analize(prueba);
        
        LinkedList<Token> tokens = lexer.getTokenList();
        System.out.println("La lista de tokens provenientes de tokenizer es: ");
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(i + ". " + tokens.get(i).getLexeme() + " | tipo: " + tokens.get(i).getTokenType());
        }
        
        System.out.println("");System.out.println("");
        
        Parser parser = new Parser();
        parser.inicializarAutomatas();
        parser.Parse(tokens);
        
        LinkedList<SyntaxToken> syntokens =   parser.getSintaxTokens();
        for (int i = 0; i < syntokens.size(); i++) {
            System.out.println("Sentencia:  " + syntokens.get(i).getSentencia()  + " | finalLista: " + syntokens.get(i).getPosicionFinalLista() + " | Tipo : " + syntokens.get(i).getTipo());
            
        }
        
        
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
