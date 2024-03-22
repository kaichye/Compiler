package parser;

import java.util.ArrayList;
import scanner.Token;


public class Param {
    Token.TokenType type;
    Expression var;
    NUM_Expression size;
    
    public Param(Token.TokenType t, Expression st, NUM_Expression si) {
        type = t;
        var = st;
        size = si;
    }
    
    public void print(String indent) {
        indent += "    ";
        if (type == Token.TokenType.VOID_TOKEN) {
            System.out.println(indent + "void");
        } else if (size.getVal().equals("0")) {
            System.out.print(indent + "int" + " ");
            var.print(indent);
            System.out.println();
        } else {
            System.out.print(indent + "int" + " ");
            var.print(indent);
            System.out.print("["); 
            size.print(indent); 
            System.out.println("]");
        }
    }
    
    public static ArrayList<Param> parseParams() {
        ArrayList<Param> params = new ArrayList<>();
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.VOID_TOKEN) {
            CMinusParser.advanceToken();
            Param p = new Param(Token.TokenType.VOID_TOKEN, new ID_Expression(""), new NUM_Expression(""));
            params.add(p);
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
            CMinusParser.advanceToken();
            ID_Expression val = new ID_Expression(CMinusParser.matchToken(Token.TokenType.ID_TOKEN));

            // Check for array
            NUM_Expression size = new NUM_Expression("0");
            if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
                CMinusParser.advanceToken();
                size = new NUM_Expression(CMinusParser.matchToken(Token.TokenType.CONST_TOKEN));
                CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            }

            Param p = new Param(Token.TokenType.INT_TOKEN, val, size);
            params.add(p);
            
            while (CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN) {
                CMinusParser.advanceToken();
                if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
                    CMinusParser.advanceToken();
                    val = new ID_Expression(CMinusParser.matchToken(Token.TokenType.ID_TOKEN));

                    // Check for array
                    size = new NUM_Expression("0");
                    if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
                        CMinusParser.advanceToken();
                        size = new NUM_Expression(CMinusParser.matchToken(Token.TokenType.CONST_TOKEN));
                        CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
                    }

                    p = new Param(Token.TokenType.INT_TOKEN, val, size);
                    params.add(p);
                }
            }
        } else {
            throw new CMinusParserException("parseParam: " + CMinusParser.currentToken.getType() + " is not INT or VOID");
        }
        
        return params;
    }
}
