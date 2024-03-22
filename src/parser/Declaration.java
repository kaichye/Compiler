package parser;

import java.util.ArrayList;
import scanner.*;

public class Declaration {
    public static Declaration parseDeclaration() {
        if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
            CMinusParser.advanceToken();
            ID_Expression val = new ID_Expression(CMinusParser.matchToken(Token.TokenType.ID_TOKEN));
            Declaration ret = parseDeclarationPrime(val);
            return ret;
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.VOID_TOKEN) {
            CMinusParser.advanceToken();
            ID_Expression val = new ID_Expression(CMinusParser.matchToken(Token.TokenType.ID_TOKEN));
            Declaration ret = parseFunDeclarationPrime(Token.TokenType.VOID_TOKEN, val);
            return ret;
        } else {
            throw new CMinusParserException("parseDeclaration: " + CMinusParser.currentToken.getType() + " is not INT or VOID");
        }
    }
    
    public static Declaration parseDeclarationPrime(ID_Expression val) {
        if (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN) {
            CMinusParser.advanceToken();
            return new Var_Declaration(Token.TokenType.INT_TOKEN, val, new NUM_Expression("0"));
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
            CMinusParser.advanceToken();
            NUM_Expression size = new NUM_Expression(CMinusParser.matchToken(Token.TokenType.CONST_TOKEN));
            CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
            return new Var_Declaration(Token.TokenType.INT_TOKEN, val, size);
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            return parseFunDeclarationPrime(Token.TokenType.INT_TOKEN, val);
        } else {
            throw new CMinusParserException("parseDeclarationPrime: " + CMinusParser.currentToken.getType() + " is not ; or [ or (");
        }
    }
    
    public static Declaration parseFunDeclarationPrime(Token.TokenType type, ID_Expression val) {
        CMinusParser.matchToken(Token.TokenType.OPEN_PAREN_TOKEN);
        ArrayList<Param> params = Param.parseParams();
        CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
        Compound_Statement cs = Statement.parseCompoundStmt();
        return new Fun_Declaration(type, val, params, cs);
    }
    
    public void print(String indent) {}
}
