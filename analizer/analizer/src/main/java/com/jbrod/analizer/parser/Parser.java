
package com.jbrod.analizer.parser;

import com.jbrod.analizer.parser.tokens.SyntaxToken;
import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.parser.automatas.Recurrentes;
import com.jbrod.analizer.parser.tokens.CodeBlock;
import com.jbrod.analizer.parser.tokens.Funcion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que inicia el análisis sintáctico usando autómatas finitos deterministas.
 * @author Jorge
 */
public class Parser {
    
    private LinkedList <Token> lexTokens; // -> Tokens provenientes del lexer
    private LinkedList <SyntaxToken> sintaxTokens; //-> Tokens sintácticos
    private LinkedList <SyntaxToken> codeblockTokens; // -> Tokens sintácticos con bloques de codigo dentro

    
    // - Atomatas a usar --------------------------------------
    private Recurrentes recurrentes; 
    
    private int iterador; // -> iterador encargado de llevar el control
    
    public Parser() {
        lexTokens = new LinkedList<>();
        sintaxTokens = new LinkedList<>();
        codeblockTokens = new LinkedList<>();
        iterador = 0; 
        
    }
    
    /**
     * Inicializa los automatas a usar.
     **/
    public void inicializarAutomatas(){
        recurrentes = new Recurrentes(sintaxTokens, this);
    }
    
    
    /**
     * Analiza la lista de tokens provenientes del lexer.
     **/
    public void Parse( LinkedList<Token> lexTokens ){
        
        //inicializarAutomatas();
        //this.lexTokens = lexTokens;

        Token tokenActual = lexTokens.get(iterador); 
        String lexemaActual = tokenActual.getLexeme();        
        
        // Recorrer la lista de tokens generados por el lexer (de manera recursiva, cada automata llama a esta funcion cuando termina)
        //for (iterador = 0; iterador < lexTokens.size(); iterador++) {
        if(iterador < lexTokens.size()){
            
                lexemaActual = lexTokens.get(iterador).getLexeme();
            
                switch(lexemaActual){
                case "range": if(recurrentes.rango          (lexTokens, iterador)){Parse(lexTokens);}; break;
                case "["    : if(recurrentes.array          (lexTokens, iterador)){Parse(lexTokens);}; break; 
                case "{"    : if(recurrentes.diccionario    (lexTokens, iterador)){Parse(lexTokens);}; break;  
                case "("    : if(recurrentes.tupla          (lexTokens, iterador)){Parse(lexTokens);}; break;  
                
                case "else" : if(recurrentes.sino           (lexTokens, iterador)){Parse(lexTokens);}; break; 
                
                
            }
            
            // Fuera del switch, lo que no se puede evaluar con el lexema, sino que necesita más info (tipo de token, etc)
        
        
        }       
        //}
        
    }
    
    
    
    
    
    public void agregarTokenSintactico(SyntaxToken token){
        //obtener el ultimo actual para comprobar la identacion
        SyntaxToken actualUltimoGeneral;
        
        if(!sintaxTokens.isEmpty()){
            actualUltimoGeneral = sintaxTokens.getLast();
            
            //Si el ultimo general es un bloque de codigo, verificar la identación 
            if(actualUltimoGeneral instanceof CodeBlock || actualUltimoGeneral instanceof Funcion){
                if(token.getColumna() > actualUltimoGeneral.getColumna()){
                    ((CodeBlock)actualUltimoGeneral).agregarToken(token);
                }
            }
        }
        
        
        //Agregar al general 
        sintaxTokens.add(token);
        
        
        
        //Si el token a agregar contiene un bloque de codigo, agregarlo a la lista de bloques de codigo
        if(token instanceof CodeBlock || token instanceof Funcion){
            codeblockTokens.add(token);
        }
        
    }
    
    
    public void actualizarIterador(int actualizacion){
        iterador = actualizacion; 
    }
    
    
    /**
     * Define el bloque de codigo donde se está trabajando.
     **/
    public void definirBloque(){
    
    }

    public LinkedList<SyntaxToken> getSintaxTokens() {
        return sintaxTokens;
    }

    public LinkedList<SyntaxToken> getCodeblockTokens() {
        return codeblockTokens;
    }
    
    
    
}
