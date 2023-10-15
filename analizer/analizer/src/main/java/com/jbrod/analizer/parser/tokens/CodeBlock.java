
package com.jbrod.analizer.parser.tokens;

import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class CodeBlock extends SyntaxToken {

    private LinkedList<SyntaxToken>  codeBlock; //lista con el bloque de codigo contenido dentro
    
    public CodeBlock(String sentencia, int fila, int columna, String tipo, int posicionFinalLista) {
        super(sentencia, fila, columna, tipo, posicionFinalLista);
        codeBlock = new LinkedList<>();
    }

    public LinkedList<SyntaxToken> getCodeBlock() {
        return codeBlock;
    }

    public void setCodeBlock(LinkedList<SyntaxToken> codeBlock) {
        this.codeBlock = codeBlock;
    }

    /**
     * Agrega un token a la lista del bloque de codigo
     **/
    public void agregarToken(SyntaxToken token){
        codeBlock.add(token);
        if(sentencia == null){
            sentencia = "";
        }
        sentencia = sentencia + token.getSentencia();
    }
    
}
