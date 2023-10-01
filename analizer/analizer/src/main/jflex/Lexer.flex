/* Generar lexer: pom -> de.jflex:jflex-maven-plugin:1.9.1:generate */

/* -------------------- Codigo de usuario  --------------------------------- -  se copia integramente en la clase  -----------------*/
package com.jbrod.analizer.lexer;
import  com.jbrod.analizer.lexer.Tokens.*;



%%
/* ------------------------------------------------ Opciones y declaraciones -------------------------------------------------- */

/*nombre*/
%class Lexer 

/* genera tipo de dato Token */   
%type Token     

/* MACROS - Expresiones regulares */
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
caddob=\".*\"
cadsimp='.*'
comment=#.*
/*comment=#.[a-zA-Z0-9.,/-+@"*'!#$%&()=?_:;><]\n*/

%{
    public String lexeme;
%}




%%
/* ------------------------------------------------------ Reglas lexicas --------------------------------------------------------- */
/* new Token(tipo, lexema, patron, fila, columna) */

/*  Identificadores  ->  [a-zA-Z_][a-zA-Z0-9_]*    */
{L}({L}|{D})* {return new Token(Tokens.IDENTIFIER, yytext(), "[a-zA-Z_][a-zA-Z0-9_]*", yyline, yycolumn);}

/* Aritmeticos */
"+"  |
"-"  |
"**" |
"/"  |
"//" |
"%"  | 
"*"  {return new Token(Tokens.ARITHMETIC, yytext(), yytext(), yyline, yycolumn);}

/* Comparacion */
"==" | 
"!=" |
">"  |
"<"  |
">=" |
"<=" {return new Token(Tokens.COMPARISION, yytext(), yytext(), yyline, yycolumn);}

/* Logicos */
"and" |
"or"  |
"not" {return new Token(Tokens.LOGICAL, yytext(), yytext(), yyline, yycolumn);}

/* Asignacion - posible combinacion aritmeticos */
"="   |
"+="  |
"-="  |
"**=" |
"/="  |
"//=" |
"%="  | 
"*="  {return new Token(Tokens.ASSIGNAMENT, yytext(), yytext(), yyline, yycolumn);}

/* Palabras clave */
"and"       |
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
"not"       |
"or"        |
"pass"      |
"raise"     |
"return"    |
"True"      |
"try"       |
"while"     |
"with"      |
"yield"     {return new Token(Tokens.KEYWORD, yytext(), yytext(), yyline, yycolumn);}

/* Constantes */
{D}+ |
{D}+"."{D}+ |
{caddob}    |
{cadsimp}   |
"true"      |
"false"     {return new Token(Tokens.CONSTANT, yytext(), yytext(), yyline, yycolumn);}

/* Comentario */
{comment} {return new Token(Tokens.COMMENT, yytext(), yytext(), yyline, yycolumn);}

/* Otros */
"(" |
")" |
"{" |
"}" |
"[" |
"]" |
"," |
";" |
":" {return new Token(Tokens.OTHERS, yytext(), yytext(), yyline, yycolumn);}

{espacio} { /* ignorar */ }

. {return new Token(Tokens.LEXICAL_ERROR_unknow_lexeme, yytext(), yytext(), yyline, yycolumn);}

