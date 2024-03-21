package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Character.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scanner for the C- programming language.
 * @author Kaicheng Ye, Elijah Solokha, Samuel Church
 * @version 1.0
 * File: CMinusScanner.java
 * Created: Feb. 2023
 * © Copyright Cedarville University, its Computer Science faculty, and the
 * authors.  All rights reserved.
 * 
 * Description: 1 Part of three for the entire compiler
 * of C-. Currently, it outputs the tokens into a file,
 * but that will change when it needs to work with the 
 * parser.
 */
public class CMinusScanner implements Scanner {

    private BufferedReader inFile;
    private Token nextToken;

    public CMinusScanner (String filename) {
        try {
            inFile = new BufferedReader(new FileReader(filename));
            try {
                nextToken = scanToken();         // next token pre-populated in the constructor
            } catch (IOException e) {
                System.out.println("scanToken is throwing an error!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, file not found!");
        }
    }
    public Token getNextToken () {       // munch up the next token
        Token returnToken = nextToken;   // save return token
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)  // NOT eof
            try {
                nextToken = scanToken();         // next token pre-populated in the constructor
            } catch (IOException e) {
                System.out.println("scanToken is throwing an error!");
            }
        return returnToken;
    }
    public Token viewNextToken () {      // just peek at the next token
        return nextToken;
    }
    
    public Token scanToken() throws IOException {
        // DFA implementation
        final int DONE = 1;
        final int START = 2;
        final int CONST = 3;
        final int ID = 4;
        final int LESS_THAN = 5;
        final int IN_COMMENT = 6;
        final int EXCLAMATION = 7;
        final int POTENTIAL_COMMENT_END = 8;
        final int SLASH = 9;
        final int GREATER_THAN = 10;
        final int EQUAL = 11;
        final int KEYWORD = 12;
        final int ERROR = 13;
        
        Token.TokenType type = null;
        
        int state = START;
        String token = "";
        
        // DFA start
        while (state != DONE) {
            inFile.mark(1);
            
            // set character to something because it can't be null
            char c = '.';
            try {
                c = (char) inFile.read();
            } catch (IOException e) {
                System.out.println("Error, cannot read character.");
            }
            switch (state) {
                case START:
                    // this is the EOF character
                    if (c == '\uffff') {
                        state = DONE;
                        type = Token.TokenType.EOF_TOKEN;
                    }
                    else if (isDigit(c)) {
                        token += c;
                        state = CONST;
                        type = Token.TokenType.CONST_TOKEN;
                    }
                    else if (isAlphabetic(c)) {
                        token += c;
                        state = ID;
                    }
                    else if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                        state = START;
                    }
                    else {
                        switch(c) {
                            case '+':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.ADD_TOKEN;
                                break;
                            case '-':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.SUBTRACT_TOKEN;
                                break;
                            case '*':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.MULTIPLY_TOKEN;
                                break;
                            case ';':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.SEMICOLON_TOKEN;
                                break;
                            case ',':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.COMMA_TOKEN;
                                break;
                            case '(':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.OPEN_PAREN_TOKEN;
                                break;
                            case ')':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.CLOSED_PAREN_TOKEN;
                                break;
                            case '[':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.OPEN_SQUARE_TOKEN;
                                break;
                            case ']':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.CLOSED_SQUARE_TOKEN;
                                break;
                            case '{':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.OPEN_CURLY_TOKEN;
                                break;
                            case '}':
                                token += c;
                                state = DONE;
                                type = Token.TokenType.CLOSED_CURLY_TOKEN;
                                break;
                            case '<':
                                token += c;
                                state = LESS_THAN;
                                type = Token.TokenType.LESS_THAN_TOKEN;
                                break;
                            case '>':
                                token += c;
                                state = GREATER_THAN;
                                type = Token.TokenType.GREATER_THAN_TOKEN;
                                break;
                            case '!':
                                token += c;
                                state = EXCLAMATION;
                                break;
                            case '=':
                                token += c;
                                state = EQUAL;
                                type = Token.TokenType.ASSIGNMENT_TOKEN;
                                break;
                            case '/':
                                token += c;
                                state = SLASH;
                                break;
                            default:
                                state = ERROR;
                                break;
                        }
                    }
                    break;
                case LESS_THAN:
                    switch(c) {
                        case '=':
                            token += c;
                            state = DONE;
                            type = Token.TokenType.LESS_THAN_EQUAL_TO_TOKEN;
                            break;
                        default:
                            state = DONE;
                            inFile.reset();
                            break;
                    }
                    break;
                case GREATER_THAN:
                    switch(c) {
                        case '=':
                            token += c;
                            state = DONE;
                            type = Token.TokenType.GREATER_THAN_EQUAL_TO_TOKEN;
                            break;
                        default:
                            state = DONE;
                            inFile.reset();
                            break;
                    }
                    break;
                case EQUAL:
                    switch(c) {
                        case '=':
                            token += c;
                            state = DONE;
                            type = Token.TokenType.EQUIVALENT_TOKEN;
                            break;
                        default:
                            state = DONE;
                            inFile.reset();
                            break;
                    }
                    break;
                case EXCLAMATION:
                    switch(c) {
                        case '=':
                            token += c;
                            state = DONE;
                            type = Token.TokenType.NOT_EQUIVALENT_TOKEN;
                            break;
                        default:
                            state = ERROR;
                            inFile.reset();
                            break;
                    }
                    break;
                case SLASH:
                    switch(c) {
                        case '*':
                            state = IN_COMMENT;
                            break;
                        default:
                            state = DONE;
                            type = Token.TokenType.DIVIDE_TOKEN;
                            inFile.reset();
                            break;
                    }
                    break;
                case IN_COMMENT:
                    switch(c) {
                        case '*':
                            state = POTENTIAL_COMMENT_END;
                            break;
                        case '\uffff':
                            state = ERROR;
                            break;
                        default:
                            break;
                    }
                    break;
                case POTENTIAL_COMMENT_END:
                    token += c;
                    
                    switch(c) {
                        case '/':
                            state = START;
                            token = "";
                            type = Token.TokenType.COMMENT_TOKEN;
                            break;
                        case '*':
                            state = POTENTIAL_COMMENT_END;
                            inFile.reset();
                            break;
                        case '\uffff':
                            state = ERROR;
                            break;
                        default:
                            state = IN_COMMENT;
                            inFile.reset();
                            break;
                    }
                    break;
                case ID:
                    if (!isAlphabetic(c)) {
                        if (isDigit(c)) {
                            state = ERROR;
                            inFile.reset();
                        } else {
                            state = KEYWORD;
                            inFile.reset();
                        }
                    } else {
                        token += c;
                    }
                    break;
                case CONST:
                    if (!isDigit(c)) {
                        if (isAlphabetic(c)) {
                            state = ERROR;
                            inFile.reset();
                        } else {
                            state = DONE;
                            type = Token.TokenType.CONST_TOKEN;
                            inFile.reset();
                        }
                    } else {
                        token += c;
                    }
                    break;
                case KEYWORD:
                    // check for keyword, other ID
                    state = DONE;
                    inFile.reset();
                    switch (token) {
                        case "else":
                            type = Token.TokenType.ELSE_TOKEN;
                            break;
                        case "if":
                            type = Token.TokenType.IF_TOKEN;
                            break;
                        case "int":
                            type = Token.TokenType.INT_TOKEN;
                            break;
                        case "return":
                            type = Token.TokenType.RETURN_TOKEN;
                            break;
                        case "void":
                            type = Token.TokenType.VOID_TOKEN;
                            break;
                        case "while":
                            type = Token.TokenType.WHILE_TOKEN;
                            break;
                        default:
                            type = Token.TokenType.ID_TOKEN;
                            break;
                    }
                    break;
                case ERROR:
                    type = Token.TokenType.ERROR_TOKEN;
                    state = DONE;
                    inFile.reset();
                    break;
                default:
                    break;
            }
        }
        return new Token(type, token);
    }
    
    // main goes here in project 1
    public static void main(String[] args) throws IOException {
        CMinusScanner scanner = new CMinusScanner("C:\\Users\\kaich\\OneDrive - Cedarville University\\Classes\\Junior\\Spring\\Compiler\\Project\\Compiler\\src\\scanner\\test2.txt");
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\kaich\\OneDrive - Cedarville University\\Classes\\Junior\\Spring\\Compiler\\Project\\Compiler\\src\\scanner\\scanned.txt"));

        Token currToken = new Token(Token.TokenType.EOF_TOKEN);
        
        // do-while to allow for currToken to be initialized to something
        do {
            String line = "";
            currToken = scanner.getNextToken();
            line += currToken.getType();
            
            // print out value associated with ID or CONST
            if (currToken.getType() == Token.TokenType.ID_TOKEN || 
                  currToken.getType() == Token.TokenType.CONST_TOKEN) {
                line += " " + currToken.getData();
            }
            line += "\n";
            
            writer.write(line);
        } while (currToken.getType() != Token.TokenType.EOF_TOKEN);
        writer.close();
    }
}
