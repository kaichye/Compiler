package parser;

import lowlevel.*;

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
    
    @Override
    public void genLLCode(Function func) {
        BasicBlock curr = func.getCurrBlock();
        
        Operation newOper = new Operation(Operation.OperationType.ASSIGN, curr);
        Operand src = new Operand(Operand.OperandType.REGISTER, exp.genLLCode(func));
        Operand dst = new Operand(Operand.OperandType.MACRO,"RetReg");
        newOper.setSrcOperand(0, src);
        newOper.setDestOperand(0, dst);
        curr.appendOper(newOper);
    }
}
