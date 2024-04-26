package parser;

import lowlevel.*;

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
        indent += "    ";
        System.out.println(indent + "if (");
        exp.print(indent + "    ");
        System.out.println(indent + "    " + ")");
        if (if_stmt instanceof Compound_Statement && ((Compound_Statement)if_stmt).getSize() > 1) {
            indent += "    ";
            System.out.println(indent + "{");
        }
        if_stmt.print(indent);
        if (if_stmt instanceof Compound_Statement && ((Compound_Statement)if_stmt).getSize() > 1) {
            System.out.println(indent + "}");
        }
        if (else_stmt != null) {
            System.out.print(indent + "else");
            if (else_stmt instanceof Compound_Statement && ((Compound_Statement)else_stmt).getSize() > 1) {
                System.out.println(" {");
            } else {
                System.out.println("");
            }
            else_stmt.print(indent);
            if (else_stmt instanceof Compound_Statement && ((Compound_Statement)else_stmt).getSize() > 1) {
                System.out.println(indent + "}");
            }
        }
    }
    
    @Override
    public void genLLCode(Function func) {
        BasicBlock curr = func.getCurrBlock();
        
        int condition = exp.genLLCode(func);
        
        BasicBlock then = new BasicBlock(func);
        BasicBlock post = new BasicBlock(func);
        
        if (else_stmt != null) {
            BasicBlock elseBlock = new BasicBlock(func);
            
            Operation newOper = new Operation(Operation.OperationType.BEQ, curr);
            Operand src1 = new Operand(Operand.OperandType.REGISTER, condition);
            Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
            Operand src3 = new Operand(Operand.OperandType.BLOCK, elseBlock.getBlockNum());
            newOper.setSrcOperand(0, src1);
            newOper.setSrcOperand(1, src2);
            newOper.setSrcOperand(2, src3);
            curr.appendOper(newOper);
            
            func.appendToCurrentBlock(then);
            func.setCurrBlock(then);
            if_stmt.genLLCode(func);
            
            func.appendToCurrentBlock(post);
            func.setCurrBlock(elseBlock);
            
            else_stmt.genLLCode(func);
            
            Operation newOper2 = new Operation(Operation.OperationType.JMP, func.getCurrBlock());
            Operand src = new Operand(Operand.OperandType.BLOCK, post.getBlockNum());
            newOper2.setSrcOperand(0, src);
            func.getCurrBlock().appendOper(newOper2);
            
            func.appendUnconnectedBlock(elseBlock);
            
            func.setCurrBlock(post);
            
        } else {
            Operation newOper = new Operation(Operation.OperationType.BEQ, curr);
            Operand src1 = new Operand(Operand.OperandType.REGISTER, condition);
            Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
            Operand src3 = new Operand(Operand.OperandType.BLOCK, post.getBlockNum());
            newOper.setSrcOperand(0, src1);
            newOper.setSrcOperand(1, src2);
            newOper.setSrcOperand(2, src3);
            curr.appendOper(newOper);

            func.appendToCurrentBlock(then);
            func.setCurrBlock(then);
            if_stmt.genLLCode(func);


            func.appendToCurrentBlock(post);
            func.setCurrBlock(post);
        }
    }
}
