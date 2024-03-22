package parser;

import scanner.Token;
import java.util.ArrayList;

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
            e = new Binary_Expression(Token.TokenType.ASSIGNMENT_TOKEN, id, rhs);
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
            e = new Binary_Expression(Token.TokenType.ASSIGNMENT_TOKEN, bracketExp, rhs);          
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
}

