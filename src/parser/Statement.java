package parser;

import java.util.ArrayList;
import scanner.Token;
import lowlevel.*;


public class Statement {
    public static Compound_Statement parseCompoundStmt() {
        CMinusParser.matchToken(Token.TokenType.OPEN_CURLY_TOKEN);
        
        ArrayList<Var_Declaration> local_decl = parseLocalDecl();
        ArrayList<Statement> statement_list = parseStatementList();
        
        CMinusParser.matchToken(Token.TokenType.CLOSED_CURLY_TOKEN);
        
        return new Compound_Statement(local_decl, statement_list);
    }
    
    public static ArrayList<Var_Declaration> parseLocalDecl() {
        ArrayList<Var_Declaration> local_decl = new ArrayList<>();
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
            while(CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
                CMinusParser.advanceToken();
                ID_Expression val = new ID_Expression(CMinusParser.matchToken(Token.TokenType.ID_TOKEN));

                // Check for array
                NUM_Expression size = new NUM_Expression("0");
                if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
                    CMinusParser.advanceToken();
                    size = new NUM_Expression(CMinusParser.matchToken(Token.TokenType.CONST_TOKEN));
                    CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
                }

                CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);

                Var_Declaration v = new Var_Declaration(Token.TokenType.INT_TOKEN, val, size);
                local_decl.add(v);
            }
        } else if (CMinusParser.currentToken.getType() != Token.TokenType.SEMICOLON_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.OPEN_CURLY_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.IF_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.WHILE_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.RETURN_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.ID_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.CONST_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.OPEN_PAREN_TOKEN && 
                CMinusParser.currentToken.getType() != Token.TokenType.CLOSED_CURLY_TOKEN) {
            throw new CMinusParserException("parseLocalDecl: " + CMinusParser.currentToken.getType() + " is not in First or Follow set of local-decl");
        }

        return local_decl;
    }
    
    public static Statement parseStatement() {
        Statement s;
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN){
            s = parseExpressionStmt();
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.IF_TOKEN){
            s = parseSelectionStmt();
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.WHILE_TOKEN){
            s = parseIterationStmt();
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.RETURN_TOKEN){
            s = parseReturnStmt();
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_CURLY_TOKEN){
            s = parseCompoundStmt();
        } else {
            throw new CMinusParserException("parseStatement: " + CMinusParser.currentToken.getType() + " is not in First set of statement");
        }
        
        return s;
    }
    
    
    public static ArrayList<Statement> parseStatementList() {
        ArrayList<Statement> statement_list = new ArrayList<>();
        
        while (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.OPEN_CURLY_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.IF_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.WHILE_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.RETURN_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) { 
            
            statement_list.add(parseStatement());
        }
        
        return statement_list;
    }
    
    public static Expression_Statement parseExpressionStmt() {
        Expression exp = null;
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            exp = Expression.parseExpression();
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
        } else {
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
        }
        
        return new Expression_Statement(exp);
    }
    
    public static Selection_Statement parseSelectionStmt() {
        Expression exp;
        Statement if_stmt;
        Statement else_stmt = null;
        
        CMinusParser.matchToken(Token.TokenType.IF_TOKEN);
        CMinusParser.matchToken(Token.TokenType.OPEN_PAREN_TOKEN);
        exp = Expression.parseExpression();
        CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
        if_stmt = parseStatement();
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.ELSE_TOKEN) {
            CMinusParser.advanceToken();
            else_stmt = parseStatement();
        }
        
        return new Selection_Statement(exp, if_stmt, else_stmt);
    }
    
    public static Iteration_Statement parseIterationStmt() {
        Expression exp;
        Statement stmt;
        
        CMinusParser.matchToken(Token.TokenType.WHILE_TOKEN);
        CMinusParser.matchToken(Token.TokenType.OPEN_PAREN_TOKEN);
        exp = Expression.parseExpression();
        CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
        stmt = parseStatement();
        
        return new Iteration_Statement(exp, stmt);
    }
    
    public static Return_Statement parseReturnStmt() {
        Expression exp = null;
        
        CMinusParser.matchToken(Token.TokenType.RETURN_TOKEN);
        
        if (CMinusParser.currentToken.getType() == Token.TokenType.ID_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.CONST_TOKEN || 
                CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            exp = Expression.parseExpression();
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
        } else {
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
        }
        
        return new Return_Statement(exp);
    }
    
    public void print(String indent) {}
    
    public void genLLCode(Function func) {}
}
