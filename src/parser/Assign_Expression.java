package parser;

import java.util.ArrayList;
import scanner.*;

import lowlevel.*;


public class Assign_Expression extends Expression {
    Expression lhs;
    Expression rhs;
    
    public Assign_Expression(Expression e1, Expression e2) {
        lhs = e1;
        rhs = e2;
    }
    
    
    @Override
    public void print(String indent) {
        indent += "    ";
        
        String opPrint = "=";

        
        System.out.println(indent + opPrint);
        if (lhs instanceof ID_Expression || lhs instanceof NUM_Expression) {
            System.out.print(indent + "    ");
        }
        lhs.print(indent);
        System.out.println();
        if (rhs instanceof ID_Expression || rhs instanceof NUM_Expression) {
            System.out.print(indent + "    ");
        }
        rhs.print(indent);
        if (rhs instanceof ID_Expression || rhs instanceof NUM_Expression) {
            System.out.println();
        }
    }
    
    @Override
    public int genLLCode(Function func) {
        int regNum;
        
        BasicBlock curr = func.getCurrBlock();
        
        int lReg = lhs.genLLCode(func);
        int rReg = rhs.genLLCode(func);
        
        
        if (func.getTable().containsKey(((ID_Expression)lhs).val)) {
            Operation newOper = new Operation(Operation.OperationType.ASSIGN, curr);
            Operand src = new Operand(Operand.OperandType.REGISTER, rReg);
            Operand dst = new Operand(Operand.OperandType.REGISTER, lReg);
            newOper.setSrcOperand(0, src);
            newOper.setDestOperand(0, dst);
            curr.appendOper(newOper);
            
            regNum = lReg;
        } else {
            Operation newOper = new Operation(Operation.OperationType.STORE_I, curr);
            Operand src = new Operand(Operand.OperandType.REGISTER, rReg);
            Operand src1 = new Operand(Operand.OperandType.STRING, ((ID_Expression)lhs).val);
           
            newOper.setSrcOperand(0, src);
            newOper.setSrcOperand(1, src1);
            curr.appendOper(newOper);
            
            regNum = rReg;
        }
        
        return regNum;
    }
}
