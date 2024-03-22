package parser;

import scanner.*;

public class Var_Declaration extends Declaration {
    Token.TokenType type;
    ID_Expression var;
    NUM_Expression size;
    
    public Var_Declaration(Token.TokenType t, ID_Expression st, NUM_Expression si) {
        type = t;
        var = st;
        size = si;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        if (size.getVal().equals("0")) {
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
}

