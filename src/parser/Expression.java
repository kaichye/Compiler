package parser;

import scanner.Token;
import java.util.ArrayList;
import lowlevel.*;

public class Expression {
   
    public static Expression parseExpression() {
        Expression e;
        if(CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN){
            String idStr = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);
            ID_Expression id = new ID_Expression(idStr);
            e = Expression.parseExpressionPrime(id);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN){
            String numStr = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
            Expression num = new NUM_Expression(numStr);
            e = Binary_Expression.parseSimpleExpressionPrime(num);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN){
            CMinusParser.advanceToken();
            Expression parenExp = parseExpression();
            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
            e = Binary_Expression.parseSimpleExpressionPrime(parenExp);            
        } else{
            throw new CMinusParserException("parseExpression: " + CMinusParser.currentToken.getType() +
                    "is not ID_TOKEN, CONST_TOKEN, or OPEN_PAREN_TOKEN");
        }
        return e;
    }
   
    public static Expression parseExpressionPrime(ID_Expression id){
        Expression e;
        if(CMinusParser.currentToken.getType() == Token.TokenType.ASSIGNMENT_TOKEN){
            CMinusParser.advanceToken();
            Expression rhs = parseExpression();
            e = new Assign_Expression(id, rhs);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN){
            CMinusParser.advanceToken();
            Expression arrayExp = parseExpression();
            Bracket_Expression bracketExp = new Bracket_Expression(id, arrayExp);
            CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            e = parseExpressionPrimePrime(bracketExp);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN){
            CMinusParser.advanceToken();
            ArrayList<Expression> args = parseArgs();
            Expression callExp = new Call_Expression(id, args);
            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
            e = Binary_Expression.parseSimpleExpressionPrime(callExp);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN){
           
            e = Binary_Expression.parseSimpleExpressionPrime(id);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN){
           
            e = id;
        } else {
            throw new CMinusParserException("parseExpressionPrime: " + CMinusParser.currentToken.getType() +
                    "is not =, [, (, +, -, <=, <, >, >=, ==, !=, *, /, ;, ), ], or ,");
        }
        return e;
    }
   
    public static Expression parseExpressionPrimePrime(Bracket_Expression bracketExp){
        Expression e;
        if(CMinusParser.currentToken.getType() == Token.TokenType.ASSIGNMENT_TOKEN){
            CMinusParser.advanceToken();
            Expression rhs = parseExpression();
            e = new Assign_Expression(bracketExp, rhs);          
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN){
           
            e = Binary_Expression.parseSimpleExpressionPrime(bracketExp);
        } else if(CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
                CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN){
           
            e = null;
        }
        else{
            throw new CMinusParserException("parseExpressionPrimePrime: " + CMinusParser.currentToken.getType() +
                    "is not =, +, -, <=, <, >, >=, ==, !=, *, /, ;, ), ], or ,");
        }
        return e;
    }

   
    public static ArrayList<Expression> parseArgs() {
        if (CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN) {
            return null;
        }
       
        ArrayList<Expression> args = new ArrayList<>();
       
        Expression exp = parseExpression();
        args.add(exp);
       
        while (CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN) {
            CMinusParser.advanceToken();
            exp = parseExpression();
            args.add(exp);
        }
       
        return args;
    }
   
    public void print(String indent) {}
    
    public static Expression parseSimpleExpressionPrime(Expression lhs) {
        lhs = parseAdditiveExpressionPrime(lhs);
        
        Token.TokenType op;
        if (CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
            op = CMinusParser.currentToken.getType();
            CMinusParser.advanceToken();
            Expression rhs = parseAdditiveExpression();
            return new Binary_Expression(op, lhs, rhs);
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN) {
            return lhs;
        } else {
            throw new CMinusParserException("parseSimpleExpressionPrime: " + CMinusParser.currentToken.getType() + " is not in First or Follow set of SimpleExpressionPrime");
        }
    }
    
    public static Expression parseAdditiveExpression() {
        Expression lhs = parseTerm();
        
        while (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN) {
            Token.TokenType op = CMinusParser.currentToken.getType();
            CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = new Binary_Expression(op, lhs, rhs);
        }
        
        return lhs;
    }
    
    public static Expression parseAdditiveExpressionPrime(Expression lhs) {
        lhs = parseTermPrime(lhs);
        
        while (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN) {
            Token.TokenType op = CMinusParser.currentToken.getType();
            CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = new Binary_Expression(op, lhs, rhs);
        }
        
        return lhs;
    }
    
    public static Expression parseTerm() {
        Expression lhs = parseFactor();
        
        while (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN) {
            Token.TokenType op = CMinusParser.currentToken.getType();
            CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = new Binary_Expression(op, lhs, rhs);
        }
        
        return lhs;
    }
    
    public static Expression parseTermPrime(Expression lhs) {
        while (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN) {
            Token.TokenType op = CMinusParser.currentToken.getType();
            CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = new Binary_Expression(op, lhs, rhs);
        }
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {
            return lhs;
        } else {
            throw new CMinusParserException("parseTermPrime: " + CMinusParser.currentToken.getType() + " is not in First or Follow set of TermPrime");
        }
    }
    
    public static Expression parseFactor() {
        if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            CMinusParser.advanceToken();
            Expression parseExpression = Expression.parseExpression();
            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
            return parseExpression;
        }
        else if (CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN) {
            String string_id = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);
            Expression varCall = parseVarCall(new ID_Expression(string_id));
            return varCall;
        }
        else if (CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN) {
            String num = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
            Expression con = new NUM_Expression(num);
            return con;
        }
        else {
            throw new CMinusParserException("parseFactor: " + CMinusParser.currentToken.getType() + " is not ( or ID or NUM");
        }
    }
    
    public static Expression parseVarCall(ID_Expression id) {
        if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
            CMinusParser.advanceToken();
            Expression arrayNum = Expression.parseExpression();
            CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            return new Bracket_Expression(id, arrayNum);
        }
        else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            CMinusParser.advanceToken();
            ArrayList<Expression> args = Expression.parseArgs();
            CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
            return new Call_Expression(id, args);
        }
        else if (CMinusParser.currentToken.getType() == Token.TokenType.MULTIPLY_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.DIVIDE_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.ADD_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.SUBTRACT_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_PAREN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.CLOSED_SQUARE_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.COMMA_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.LESS_THAN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.EQUIVALENT_TOKEN ||
                 CMinusParser.currentToken.getType() == Token.TokenType.NOT_EQUIVALENT_TOKEN) {

            return id;
        }
        else {
            throw new CMinusParserException("parseVarCall: " + CMinusParser.currentToken.getType() + " is not [ or ( or in the follow set");
        }
    }
    
    public int genLLCode(Function func) {
        return 0;
    }
}

