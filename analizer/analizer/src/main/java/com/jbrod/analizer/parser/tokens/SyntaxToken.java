
package com.jbrod.analizer.parser.tokens;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class SyntaxToken {

    String sentencia; 
    int fila; 
    int columna; 
    String tipo;
    
    //posiblemente una lista de este mismo token, pero hay que evaluarlo, añadir lista con apuntadores a sentencias que guarden bloques de codigo
    //List<SyntaxToken> bloqueDeCodigo;
    
    //indica la posicion en la lista del ultimo token lexico para continuar el analisis
    int posicionFinalLista;

    /**
     * Token de sentencia analizado sintácticamente.
     * @param sentencia: String de la sentencia guardada (usar un acumulador).
     * @param fila: fila en donde se detectó el inicio de la sentencia.
     * @param columa: columna en donde se detectó el inicio de la sentencia.
     * @param posicionFinalLista: posicion en la lista de tokens lexicos (lexer) en donde se finalizó el reconocimiento de la sentencia.
     **/
    public SyntaxToken(String sentencia, int fila, int columna, String tipo, int posicionFinalLista) {
        this.sentencia = sentencia;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.posicionFinalLista = posicionFinalLista;
    }
    
    
    /*public void inicializarBloqueDeCodigo(){
        bloqueDeCodigo = new LinkedList<>();
    }*/
    
    

    public String getSentencia() {
        return sentencia;
    }

    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPosicionFinalLista() {
        return posicionFinalLista;
    }

    public void setPosicionFinalLista(int posicionFinalLista) {
        this.posicionFinalLista = posicionFinalLista;
    }
    
    
    
    
}
