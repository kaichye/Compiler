package parser;

public class Return_Statement extends Statement {
    Expression exp;
    
    public Return_Statement(Expression e) {
        exp = e;
    }
    
    @Override
    public void print(String indent) {
        exp.print(indent);
    }
}
