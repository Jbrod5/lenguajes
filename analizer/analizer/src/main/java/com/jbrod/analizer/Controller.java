
package com.jbrod.analizer;

import com.jbrod.analizer.lexer.Lexer;
import com.jbrod.analizer.lexer.Token;
import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.CodeBlock;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class Controller {

    private Lexer lexer; 
    private LinkedList<Token> tokens; 
    
    private Parser parser; 

    public Controller() {
        lexer = new Lexer();
        tokens = new LinkedList<>();
        
        parser = new Parser();
        parser.inicializarAutomatas();
    }
    
    
    
    public void analize( String code ) throws IOException{
        lexer.Analize(code);
        tokens = lexer.getTokenList();
        parser.limpiarListas();
        parser.Parse(tokens);
        
    }

    
    public void lex( String code ) throws IOException{
        lexer.Analize(code);
        tokens = lexer.getTokenList();
    }
    
    public LinkedList<Token> getTokens() {
        return tokens;
    }
    
    public LinkedList<SyntaxToken> getGeneralSyntax(){
        return parser.getSintaxTokens();
    }
    
    public LinkedList<CodeBlock> getBlocksSyntax(){
        return parser.getCodeblockTokens();
    }    
}
