package parser;

import java.util.ArrayList;
import scanner.Token;


public class Param {
    Token.TokenType type;
    String var;
    String size;
    
    public Param(Token.TokenType t, String st, String si) {
        type = t;
        var = st;
        size = si;
    }
    
    public void print(String indent) {
        indent += "    ";
        if (type == Token.TokenType.VOID_TOKEN) {
            System.out.println(indent + "void");
        } else if (size.equals("0")) {
            System.out.println(indent + "int" + " " + var);
        } else {
            System.out.println(indent + "int" + " " + var + "[" + size + "]");
        }
    }
    
    public static ArrayList<Param> parseParams() {
        ArrayList<Param> params = new ArrayList<>();
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.VOID_TOKEN) {
            CMinusParser.advanceToken();
            Param p = new Param(Token.TokenType.VOID_TOKEN, "", "");
            params.add(p);
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
            CMinusParser.advanceToken();
            //FIXME TODO replace String with Expression
            String val = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);

            // Check for array
            String size = "0";
            if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
                CMinusParser.advanceToken();
                size = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
                CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            }

            Param p = new Param(Token.TokenType.INT_TOKEN, val, size);
            params.add(p);
            
            while (CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN) {
                CMinusParser.advanceToken();
                if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
                    CMinusParser.advanceToken();
                    //FIXME TODO replace String with Expression
                    val = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);

                    // Check for array
                    size = "0";
                    if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
                        CMinusParser.advanceToken();
                        size = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
                        CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
                    }

                    CMinusParser.matchToken(Token.TokenType.COMMA_TOKEN);

                    p = new Param(Token.TokenType.INT_TOKEN, val, size);
                    params.add(p);
                }
            }
        } else {
            throw new CMinusException("parseParam: " + CMinusParser.currentToken.getType() + " is not INT or VOID");
        }
        
        return params;
    }
}
