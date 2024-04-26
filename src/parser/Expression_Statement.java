package parser;

import lowlevel.Function;


public class Expression_Statement extends Statement {
    Expression exp;
    
    public Expression_Statement(Expression e) {
        exp = e;
    }
    
    @Override
    public void print(String indent) {
        exp.print(indent);
    }
    
    @Override
    public void genLLCode(Function func) {
        exp.genLLCode(func);
    }
}
