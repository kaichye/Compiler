package parser;

import java.util.ArrayList;
import lowlevel.*;


public class Call_Expression extends Expression {
    ID_Expression id;
    ArrayList<Expression> args;
    
    public Call_Expression(ID_Expression e, ArrayList<Expression> a) {
        id = e;
        args = a;
    }
    
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.print(indent);
        id.print(indent);
        System.out.println("(");
        indent += "    ";
        if (args == null) {
            System.out.println(indent + "    void");
        } else {
            for (int i = 0; i < args.size(); i++) {
                if (args.get(i) instanceof ID_Expression || args.get(i) instanceof NUM_Expression) {
                    System.out.print(indent + "    ");
                }
                args.get(i).print(indent);
                if (args.get(i) instanceof ID_Expression || args.get(i) instanceof NUM_Expression) {
                    System.out.println();
                }
            }
        }
        System.out.println(indent + ")");
    }
    
    @Override
    public int genLLCode(Function func) {
        int regNum = func.getNewRegNum();
        
        BasicBlock curr = func.getCurrBlock();
        
        for (int i = 0; i < args.size(); i++) {
            Operation newOper = new Operation(Operation.OperationType.PASS, curr);
            Operand temp = new Operand(Operand.OperandType.REGISTER, args.get(i).genLLCode(func));
            newOper.setSrcOperand(0, temp);
            newOper.addAttribute(new Attribute("PARAM_NUM", ((Integer)i).toString()));
            curr.appendOper(newOper);
        }
        
        Operation newOper2 = new Operation(Operation.OperationType.CALL, curr);
        Operand src = new Operand(Operand.OperandType.STRING, id.val);
        newOper2.setSrcOperand(0, src);
        newOper2.addAttribute(new Attribute("numParams", ((Integer)args.size()).toString()));
        curr.appendOper(newOper2);
        
        
        Operation newOper = new Operation(Operation.OperationType.ASSIGN, curr);
        src = new Operand(Operand.OperandType.MACRO, "RetReg");
        Operand dst = new Operand(Operand.OperandType.REGISTER, regNum);
        newOper.setSrcOperand(0, src);
        newOper.setDestOperand(0, dst);
        curr.appendOper(newOper);
       
        
        return regNum;
    }
}
