package parser;

import java.util.ArrayList;
import scanner.Token;


public class Fun_Declaration extends Declaration {
    Token.TokenType type;
    ID_Expression val;
    ArrayList<Param> params;
    Compound_Statement cs;
    
    public Fun_Declaration(Token.TokenType t, ID_Expression s, ArrayList<Param> p, Compound_Statement c) {
        type = t;
        val = s;
        params = p;
        cs = c;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        if (type == Token.TokenType.INT_TOKEN) {
            System.out.print(indent + "int ");
            val.print(indent);
            System.out.println(" (");
        } else if (type == Token.TokenType.VOID_TOKEN) {
            System.out.print(indent + "void ");
            val.print(indent); 
            System.out.println(" (");
        }
        indent += "    ";
        for (int i = 0; i < params.size(); i++) {
            params.get(i).print(indent);
        }
        System.out.println(indent + ")");
        
        // print compound statement
        System.out.println(indent + "{");
        cs.print(indent);
        System.out.println(indent + "}");
    }
}
