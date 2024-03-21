package parser;

import java.util.ArrayList;
import scanner.*;

public class Declaration {
    public static Declaration parseDeclaration() {
        if (CMinusParser.currentToken.getType() == Token.TokenType.INT_TOKEN) {
            CMinusParser.advanceToken();
            //FIXME TODO replace String with Expression
            String val = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);
            Declaration ret = parseDeclarationPrime(val);
            return ret;
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.VOID_TOKEN) {
            CMinusParser.advanceToken();
            //FIXME TODO replace String with Expression
            String val = CMinusParser.matchToken(Token.TokenType.ID_TOKEN);
            Declaration ret = parseFunDeclarationPrime(Token.TokenType.VOID_TOKEN, val);
            return ret;
        } else {
            throw new CMinusException("parseDeclaration: " + CMinusParser.currentToken.getType() + " is not INT or VOID");
        }
    }
    
    public static Declaration parseDeclarationPrime(String val) {
        if (CMinusParser.currentToken.getType() == Token.TokenType.SEMICOLON_TOKEN) {
            CMinusParser.advanceToken();
            return new Var_Declaration(Token.TokenType.INT_TOKEN, val, "0");
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_SQUARE_TOKEN) {
            CMinusParser.advanceToken();
            String size = CMinusParser.matchToken(Token.TokenType.CONST_TOKEN);
            CMinusParser.matchToken(Token.TokenType.CLOSED_SQUARE_TOKEN);
            CMinusParser.matchToken(Token.TokenType.SEMICOLON_TOKEN);
            return new Var_Declaration(Token.TokenType.INT_TOKEN, val, size);
        } else if (CMinusParser.currentToken.getType() == Token.TokenType.OPEN_PAREN_TOKEN) {
            return parseFunDeclarationPrime(Token.TokenType.INT_TOKEN, val);
        } else {
            throw new CMinusException("parseDeclarationPrime: " + CMinusParser.currentToken.getType() + " is not ; or [ or (");
        }
    }
    
    public static Declaration parseFunDeclarationPrime(Token.TokenType type, String val) {
        CMinusParser.matchToken(Token.TokenType.OPEN_PAREN_TOKEN);
        ArrayList<Param> params = Param.parseParams();
        CMinusParser.matchToken(Token.TokenType.CLOSED_PAREN_TOKEN);
        Compound_Statement cs = Statement.parseCompoundStmt();
        return new Fun_Declaration(type, val, params, cs);
    }
    
    public void print(String indent) {}
}
