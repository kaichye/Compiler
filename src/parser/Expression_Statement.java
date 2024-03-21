package parser;


public class Expression_Statement extends Statement {
    Expression exp;
    
    public Expression_Statement(Expression e) {
        exp = e;
    }
    
    @Override
    public void print(String indent) {
        exp.print(indent);
    }
}
