package parser;

import scanner.*;
import lowlevel.*;

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
    
    @Override
    public void genLLCode(Function func) {
        BasicBlock curr = func.getCurrBlock();
        
        int condition = exp.genLLCode(func);
        
        Operation newOper = new Operation(Operation.OperationType.BEQ, curr);
        Operand src1 = new Operand(Operand.OperandType.REGISTER, condition);
        Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
        Operand src3 = new Operand(Operand.OperandType.BLOCK, ?);
        newOper.setSrcOperand(0, src1);
        newOper.setSrcOperand(1, src2);
        newOper.setSrcOperand(2, src3);
        curr.appendOper(newOper);
        
    }
}
