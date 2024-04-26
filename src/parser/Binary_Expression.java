package parser;

import java.util.ArrayList;
import scanner.*;
import lowlevel.*;


public class Binary_Expression extends Expression {
    Token.TokenType op;
    Expression lhs;
    Expression rhs;
    
    public Binary_Expression(Token.TokenType t ,Expression e1, Expression e2) {
        op = t;
        lhs = e1;
        rhs = e2;
    }
    
    
    @Override
    public void print(String indent) {
        indent += "    ";
        
        String opPrint = "";
        
        if (op == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN) {
            opPrint = "<=";
        } else if (op == Token.TokenType.LESS_THAN_TOKEN) {
            opPrint = "<";
        } else if (op == Token.TokenType.GREATER_THAN_TOKEN) {
            opPrint = ">";
        } else if (op == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN) {
            opPrint = ">=";
        } else if (op == Token.TokenType.EQUIVALENT_TOKEN) {
            opPrint = "==";
        } else if (op == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
            opPrint = "!=";
        } else if (op == Token.TokenType.ADD_TOKEN) {
            opPrint = "+";
        } else if (op == Token.TokenType.SUBTRACT_TOKEN) {
            opPrint = "-";
        } else if (op == Token.TokenType.MULTIPLY_TOKEN) {
            opPrint = "*";
        } else if (op == Token.TokenType.DIVIDE_TOKEN) {
            opPrint = "/";
        }

        
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
        int regNum = func.getNewRegNum();
        
        BasicBlock curr = func.getCurrBlock();
        
        int lReg = lhs.genLLCode(func);
        int rReg = rhs.genLLCode(func);
        
        Operation newOper = null;
        
        if (op == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN) {
            newOper = new Operation(Operation.OperationType.LTE, curr);
        } else if (op == Token.TokenType.LESS_THAN_TOKEN) {
            newOper = new Operation(Operation.OperationType.LT, curr);
        } else if (op == Token.TokenType.GREATER_THAN_TOKEN) {
            newOper = new Operation(Operation.OperationType.GT, curr);
        } else if (op == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN) {
            newOper = new Operation(Operation.OperationType.GTE, curr);
        } else if (op == Token.TokenType.EQUIVALENT_TOKEN) {
            newOper = new Operation(Operation.OperationType.EQUAL, curr);
        } else if (op == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
            newOper = new Operation(Operation.OperationType.NOT_EQUAL, curr);
        } else if (op == Token.TokenType.ADD_TOKEN) {
            newOper = new Operation(Operation.OperationType.ADD_I, curr);
        } else if (op == Token.TokenType.SUBTRACT_TOKEN) {
            newOper = new Operation(Operation.OperationType.SUB_I, curr);
        } else if (op == Token.TokenType.MULTIPLY_TOKEN) {
            newOper = new Operation(Operation.OperationType.MUL_I, curr);
        } else if (op == Token.TokenType.DIVIDE_TOKEN) {
            newOper = new Operation(Operation.OperationType.DIV_I, curr);
        }
        
        Operand lSrc = new Operand(Operand.OperandType.REGISTER, lReg);
        Operand rSrc = new Operand(Operand.OperandType.REGISTER, rReg);
        Operand dst = new Operand(Operand.OperandType.REGISTER, regNum);
        newOper.setSrcOperand(0, lSrc);
        newOper.setSrcOperand(1, rSrc);
        newOper.setDestOperand(0, dst);
        curr.appendOper(newOper);
        
        return regNum;
    }
}
