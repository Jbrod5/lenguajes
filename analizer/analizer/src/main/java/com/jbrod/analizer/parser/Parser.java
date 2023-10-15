
package com.jbrod.analizer.parser;

import com.jbrod.analizer.parser.tokens.SyntaxToken;
import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.lexer.Tokens;
import com.jbrod.analizer.parser.automatas.Ciclos;
import com.jbrod.analizer.parser.automatas.Condicionales;
import com.jbrod.analizer.parser.automatas.Funciones;
import com.jbrod.analizer.parser.automatas.Recurrentes;
import com.jbrod.analizer.parser.automatas.VariablesAsignacion;
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
    private LinkedList <CodeBlock> codeblockTokens; // -> Tokens sintácticos con bloques de codigo dentro

    
    // - Atomatas a usar --------------------------------------
    private Recurrentes recurrentes; 
    private VariablesAsignacion variables_asignacion;
    private Condicionales condicionales;
    private Ciclos ciclos; 
    private Funciones funciones;
            
    
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
        variables_asignacion = new VariablesAsignacion(this, sintaxTokens);
        condicionales = new Condicionales(this, sintaxTokens);
        ciclos = new Ciclos(this, sintaxTokens);
        funciones = new Funciones(this, sintaxTokens);
    }
    
    public void limpiarListas(){
        codeblockTokens.clear();
        sintaxTokens.clear();
        iterador = 0; 
    }
    
    /**
     * Analiza la lista de tokens provenientes del lexer.
     **/
    public void Parse( LinkedList<Token> lexTokens ){
        
        //inicializarAutomatas();
        //this.lexTokens = lexTokens;
        
        
        // Recorrer la lista de tokens generados por el lexer (de manera recursiva, cada automata llama a esta funcion cuando termina)
        //for (iterador = 0; iterador < lexTokens.size(); iterador++) {
        
        //try{
        
            if(iterador < lexTokens.size()){
            System.out.println(iterador);
                System.out.println();
            
            Token tokenActual = lexTokens.get(iterador); 
            String lexemaActual = tokenActual.getLexeme();
            System.out.println("Lexema: " +tokenActual.getLexeme());
            
                //tokenActual = lexTokens.get(iterador);
                //lexemaActual = tokenActual.getLexeme();
            
                switch(lexemaActual){
                case "range": if(recurrentes.rango          (lexTokens, iterador)){Parse(lexTokens);}; break;
                case "["    : if(recurrentes.array          (lexTokens, iterador)){Parse(lexTokens);}; break; 
                case "{"    : if(recurrentes.diccionario    (lexTokens, iterador)){Parse(lexTokens);}; break;  
                case "("    : if(recurrentes.tupla          (lexTokens, iterador)){Parse(lexTokens);}; break;  
                
                case "else" : if(recurrentes.sino           (lexTokens, iterador)){Parse(lexTokens);}; break; 
                case "if"   : if(condicionales.condicional  (lexTokens, iterador)){Parse(lexTokens);}; break; 
                case "elif" : if(condicionales.condicional  (lexTokens, iterador)){Parse(lexTokens);}; break;
                
                case "for"  : if(ciclos.ciclo               (lexTokens, iterador)){Parse(lexTokens);}; break; 
                case "while": if(ciclos.ciclo               (lexTokens, iterador)){Parse(lexTokens);}; break;
                
                case "def"  : if(funciones.funcion          (lexTokens, iterador)){Parse(lexTokens);}; break;
                
                default:
                    if(tokenActual.getTokenType() == Tokens.IDENTIFIER){
                        if(variables_asignacion.asignacionOrDeclaracion(lexTokens, iterador)){
                            Parse(lexTokens);
                        }
                    }else if(tokenActual.getTokenType() == Tokens.COMMENT){
                        agregarTokenSintactico(new SyntaxToken(lexemaActual, tokenActual.getRow(), tokenActual.getColumn(), "Comentario", iterador));
                        iterador++;
                        System.out.println("Me encontre con comentario, iterador actual:" + iterador);
                        Parse(lexTokens);
                    }else{
                        agregarTokenSintactico(new SyntaxToken(lexemaActual, tokenActual.getRow(), tokenActual.getColumn(), "No identificado", iterador));
                        iterador++;
                        Parse(lexTokens);
                    }
        
        }
            
       }
        
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
                
                //si no es, verificar que la lista de bloques no esta vacia
            }else if(!codeblockTokens.isEmpty()){
                CodeBlock cod = codeblockTokens.getLast();
                if(token.getColumna() > cod.getColumna()){
                    cod.agregarToken(token);
                }
            }
        }
        
        
        //Agregar al general 
        sintaxTokens.add(token);
        
        
        
        //Si el token a agregar contiene un bloque de codigo, agregarlo a la lista de bloques de codigo
        if(token instanceof CodeBlock || token instanceof Funcion){
            codeblockTokens.add((CodeBlock)token);
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

    public LinkedList<CodeBlock> getCodeblockTokens() {
        return codeblockTokens;
    }
    
    
    
}
