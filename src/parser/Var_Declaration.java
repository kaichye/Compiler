package parser;

import scanner.*;

public class Var_Declaration extends Declaration {
    Token.TokenType type;
    String var;
    String size;
    
    public Var_Declaration(Token.TokenType t, String st, String si) {
        type = t;
        var = st;
        size = si;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        if (size.equals("0")) {
            System.out.println(indent + "int" + " " + var);
        } else {
            System.out.println(indent + "int" + " " + var + "[" + size + "]");
        }
    }
}

