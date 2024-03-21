package scanner;

/**
 * Token class to support C- Scanner
 * @author Kaicheng Ye, Elijah Solokha, Samuel Church
 * @version 1.0
 * File: Token.java
 * Created: Feb. 2023
 * © Copyright Cedarville University, its Computer Science faculty, and the
 * authors.  All rights reserved.
 * 
 * Description: Simple token class that is used by CMinusScanner.java.
 * It creates an enum for the types and has 2 getter functions.
 */
public class Token {

    private TokenType tokenType;
    private Object tokenData;

    public enum TokenType {
        ERROR_TOKEN,
        ID_TOKEN,
        CONST_TOKEN,
        EOF_TOKEN,
        ELSE_TOKEN,
        IF_TOKEN,
        INT_TOKEN,
        RETURN_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        ADD_TOKEN,
        SUBTRACT_TOKEN,
        MULTIPLY_TOKEN,
        DIVIDE_TOKEN,
        LESS_THAN_TOKEN,
        LESS_THAN_EQUAL_TO_TOKEN,
        GREATER_THAN_TOKEN,
        GREATER_THAN_EQUAL_TO_TOKEN,
        EQUIVALENT_TOKEN,
        NOT_EQUIVALENT_TOKEN,
        ASSIGNMENT_TOKEN,
        SEMICOLON_TOKEN,
        COMMA_TOKEN,
        OPEN_PAREN_TOKEN,
        CLOSED_PAREN_TOKEN,
        OPEN_SQUARE_TOKEN,
        CLOSED_SQUARE_TOKEN,
        OPEN_CURLY_TOKEN,
        CLOSED_CURLY_TOKEN,
        COMMENT_TOKEN
    }

    public Token (TokenType type) {
        this(type, null);
    }

    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }

    // some access methods
    /**
     * Gives back the type of token it is
     * @return the tokenType
     */
    public TokenType getType() {
        return tokenType;
    }
    
    /**
     * Gives back the data for this token
     * @return the tokenData
     */
    public Object getData() {
        return tokenData;
    }
}

