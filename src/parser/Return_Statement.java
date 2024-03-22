package parser;

public class Return_Statement extends Statement {
    Expression exp;
    
    public Return_Statement(Expression e) {
        exp = e;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.print(indent + "return ");
        if (exp != null) {
            exp.print(indent);
        } else {
            System.out.println();
        }
        if (exp instanceof ID_Expression || exp instanceof NUM_Expression) {
            System.out.println();
        }
    }
}
