package parser;

import java.util.ArrayList;


public class Call_Expression extends Expression {
    ID_Expression id;
    ArrayList<Expression> args;
    
    public Call_Expression(ID_Expression e, ArrayList<Expression> a) {
        id = e;
        args = a;
    }
    
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.print(indent);
        id.print(indent);
        System.out.println("(");
        indent += "    ";
        if (args == null) {
            System.out.println(indent + "    void");
        } else {
            for (int i = 0; i < args.size(); i++) {
                if (args.get(i) instanceof ID_Expression || args.get(i) instanceof NUM_Expression) {
                    System.out.print(indent + "    ");
                }
                args.get(i).print(indent);
                if (args.get(i) instanceof ID_Expression || args.get(i) instanceof NUM_Expression) {
                    System.out.println();
                }
            }
        }
        System.out.println(indent + ")");
    }
}
