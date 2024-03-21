package parser;


public class Selection_Statement extends Statement {
    Expression exp;
    Statement if_stmt;
    Statement else_stmt;
    
    public Selection_Statement(Expression e, Statement s1, Statement s2) {
        exp = e;
        if_stmt = s1;
        else_stmt = s2;
    }
    
    @Override
    public void print(String indent) {
        exp.print(indent);
        if_stmt.print(indent);
        else_stmt.print(indent);
    }
}
