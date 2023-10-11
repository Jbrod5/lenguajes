/* Generar lexer: pom -> de.jflex:jflex-maven-plugin:1.9.1:generate -X  */

/* -------------------- Codigo de usuario  --------------------------------- -  se copia integramente en la clase  -----------------*/
package com.jbrod.analizer.lexer;
import  com.jbrod.analizer.lexer.Tokens.*;



%%
/* ------------------------------------------------ Opciones y declaraciones -------------------------------------------------- */

/*nombre*/
%class Tokenizer  

/* genera tipo de dato Token */   
%type Token     

/* MACROS - Expresiones regulares */

//L=[a-zA-Z_]+              //letras
L=[a-zA-Z_]+                //letras

D=[0-9]+                    //Digitos

espacio=[ ]
//espacio=[ ,\t,\r]+ 

eol=[\n]                    //Fin de linea

caddob=\".*\"               //Cadena con comillas dobles
cadsimp='.*'                //Cadena con comillas simples

comment=#.*                 //Comentario
/*comment=#.[a-zA-Z0-9.,/-+@"*'!#$%&()=?_:;><]\n*/

%{
    public Token token;
%}




%%
/* ------------------------------------------------------ Reglas lexicas --------------------------------------------------------- */
/* new Token(tipo, lexema, patron, fila, columna) */


/* Otros */
"(" |
")" |
"{" |
"}" |
"[" |
"]" |
"," |
";" |
":" {        return new Token(Tokens.OTHERS, yytext(), yytext(), yyline, yycolumn); }

/* Fin de linea*/
{eol} { return new Token(Tokens.EOL, yytext(), "\n", yyline, yycolumn); }

/* Espacio */
{espacio} { return new Token(Tokens.SPACE, yytext(), "\n", yyline, yycolumn); }

/* Aritmeticos */
"+"  |
"-"  |
"**" |
"/"  |
"//" |
"%"  | 
"*"  { return new Token(Tokens.ARITHMETIC, yytext(), yytext(), yyline, yycolumn); }

/* Comparacion */
"==" | 
"!=" |
">"  |
"<"  |
">=" |
"<=" { return new Token(Tokens.COMPARISION, yytext(), yytext(), yyline, yycolumn); }

/* Logicos */
"and" |
"or"  |
"not" { return new Token(Tokens.LOGICAL, yytext(), yytext(), yyline, yycolumn); }

/* Asignacion - posible combinacion aritmeticos */
"="   |
"+="  |
"-="  |
"**=" |
"/="  |
"//=" |
"%="  | 
"*="  { return new Token(Tokens.ASSIGNAMENT, yytext(), yytext(), yyline, yycolumn); }

/* Palabras clave */
"as"        |
"assert"    |
"break"     |
"class"     |
"continue"  | 
"def"       |
"del"       | 
"elif"      | 
"else"      | 
"except"    | 
"False"     | 
"finally"   | 
"for"       |
"from"      |
"global"    |
"if"        |
"import"    |
"in"        | 
"is"        |
"lambda"    |
"None"      |
"nonlocal"  | 
"pass"      |
"raise"     |
"return"    |
"True"      |
"try"       |
"while"     |
"with"      |
"yield"     { return new Token(Tokens.KEYWORD, yytext(), yytext(), yyline, yycolumn); }

/* Integer ----------- */ 
{D}+ {return new Token(Tokens.INTEGER, yytext(), yytext(), yyline, yycolumn);}

/*  */

/* Constantes  ------- */
{D}+ |
{D}+"."{D}+ |
{caddob}    |
{cadsimp}   |
"true"      |
"false"     { return new Token(Tokens.CONSTANT, yytext(), yytext(), yyline, yycolumn); }

/*  Identificadores  ->  [a-zA-Z_][a-zA-Z0-9_]*    */
{L}({L}|{D})* { return new Token(Tokens.IDENTIFIER, yytext(), "[a-zA-Z_][a-zA-Z0-9_]*", yyline, yycolumn); } 


/* Comentario */
{comment} { return new Token(Tokens.COMMENT, yytext(), yytext(), yyline, yycolumn); }

 . { return new Token(Tokens.LEXICAL_ERROR_unknow_lexeme, yytext(), yytext(), yyline, yycolumn); } 

