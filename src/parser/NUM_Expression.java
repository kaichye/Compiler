package parser;

import lowlevel.*;

public class NUM_Expression extends Expression {
    String val;
    
    public NUM_Expression (String num) {
        val = num;
    }
    
    public String getVal() {
        return val;
    }
    
    @Override
    public void print(String indent) {
        System.out.print(val);
    }
    
    @Override
    public int genLLCode(Function func) {
        int regNum = func.getNewRegNum();
        
        BasicBlock curr = func.getCurrBlock();
        
        Operation newOper = new Operation(Operation.OperationType.ASSIGN, curr);
        Operand src = new Operand(Operand.OperandType.INTEGER, Integer.valueOf(val));
        Operand dst = new Operand(Operand.OperandType.REGISTER, regNum);
        newOper.setSrcOperand(0, src);
        newOper.setDestOperand(0, dst);
        curr.appendOper(newOper);
        
        return regNum;
    }
}
