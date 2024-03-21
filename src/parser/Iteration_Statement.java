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
        exp.print(indent);
        stmt.print(indent);
    }
}
