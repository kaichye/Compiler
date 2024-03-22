package parser;

public class Iteration_Statement extends Statement {
    Expression exp;
    Statement stmt;
    
    public Iteration_Statement(Expression e, Statement s) {
        exp = e;
        stmt = s;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.println(indent + "while (");
        indent += "    ";
        exp.print(indent);
        System.out.println(indent + ")");
        System.out.println(indent + "{");
        stmt.print(indent);
        System.out.println(indent + "}");
    }
}
