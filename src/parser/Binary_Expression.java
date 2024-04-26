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
    
    
    // FIXME TODO remove comments
//    public static Expression parseSimpleExpressionPrime(Expression lhs) {
//        lhs = parseAdditiveExpressionPrime(lhs);
//        
//        Token.TokenType op;
//        if (CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
//            op = CMinusParser.currentToken.getType();
//            CMinusParser.advanceToken();
//            Expression rhs = parseAdditiveExpression();
//            return new Binary_Expression(op, lhs, rhs);
//        } else if (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN) {
//            return lhs;
//        } else {
//            throw new CMinusParserException("parseSimpleExpressionPrime: " + CMinusParser.currentToken.getType() + " is not in First or Follow set of SimpleExpressionPrime");
//        }
//    }
//    
//    public static Expression parseAdditiveExpression() {
//        Expression lhs = parseTerm();
//        
//        while (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN) {
//            Token.TokenType op = CMinusParser.currentToken.getType();
//            CMinusParser.advanceToken();
//            Expression rhs = parseTerm();
//            lhs = new Binary_Expression(op, lhs, rhs);
//        }
//        
//        return lhs;
//    }
//    
//    public static Expression parseAdditiveExpressionPrime(Expression lhs) {
//        lhs = parseTermPrime(lhs);
//        
//        while (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN) {
//            Token.TokenType op = CMinusParser.currentToken.getType();
//            CMinusParser.advanceToken();
//            Expression rhs = parseTerm();
//            lhs = new Binary_Expression(op, lhs, rhs);
//        }
//        
//        return lhs;
//    }
//    
//    public static Expression parseTerm() {
//        Expression lhs = parseFactor();
//        
//        while (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN) {
//            Token.TokenType op = CMinusParser.currentToken.getType();
//            CMinusParser.advanceToken();
//            Expression rhs = parseFactor();
//            lhs = new Binary_Expression(op, lhs, rhs);
//        }
//        
//        return lhs;
//    }
//    
//    public static Expression parseTermPrime(Expression lhs) {
//        while (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN || 
//                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN) {
//            Token.TokenType op = CMinusParser.currentToken.getType();
//            CMinusParser.advanceToken();
//            Expression rhs = parseFactor();
//            lhs = new Binary_Expression(op, lhs, rhs);
//        }
//        
//        if (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
//            return lhs;
//        } else {
//            throw new CMinusParserException("parseTermPrime: " + CMinusParser.currentToken.getType() + " is not in First or Follow set of TermPrime");
//        }
//    }
//    
//    public static Expression parseFactor() {
//        if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
//            CMinusParser.advanceToken();
//            Expression parseExpression = Expression.parseExpression();
//            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
//            return parseExpression;
//        }
//        else if (CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN) {
//            String string_id = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);
//            Expression varCall = parseVarCall(new ID_Expression(string_id));
//            return varCall;
//        }
//        else if (CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN) {
//            String num = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
//            Expression con = new NUM_Expression(num);
//            return con;
//        }
//        else {
//            throw new CMinusParserException("parseFactor: " + CMinusParser.currentToken.getType() + " is not ( or ID or NUM");
//        }
//    }
//    
//    public static Expression parseVarCall(ID_Expression id) {
//        if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
//            CMinusParser.advanceToken();
//            Expression arrayNum = Expression.parseExpression();
//            CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
//            return new Bracket_Expression(id, arrayNum);
//        }
//        else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
//            CMinusParser.advanceToken();
//            ArrayList<Expression> args = Expression.parseArgs();
//            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
//            return new Call_Expression(id, args);
//        }
//        else if (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
//                 CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
//
//            return id;
//        }
//        else {
//            throw new CMinusParserException("parseVarCall: " + CMinusParser.currentToken.getType() + " is not [ or ( or in the follow set");
//        }
//    }
}
