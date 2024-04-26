package parser;

import lowlevel.*;

public class ID_Expression extends Expression {
    String val;
    
    public ID_Expression (String con) {
        val = con;
    }
    
    @Override
    public void print(String indent) {
        System.out.print(val);
    }
    
    @Override
    public int genLLCode(Function func) {
        int regNum;
        
        if (func.getTable().containsKey(val)) {
            regNum = (Integer)func.getTable().get(val);
        } else {
            BasicBlock curr = func.getCurrBlock();
            regNum = func.getNewRegNum();
            Operation newOper = new Operation(Operation.OperationType.LOAD_I, curr);
            Operand src = new Operand(Operand.OperandType.STRING, val);
            Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
            Operand dst = new Operand(Operand.OperandType.REGISTER, regNum);
            newOper.setSrcOperand(0, src);
            newOper.setSrcOperand(1, src2);
            newOper.setDestOperand(0, dst);
            curr.appendOper(newOper);
        }
        
        return regNum;
    }
}
