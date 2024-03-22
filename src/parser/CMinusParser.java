package parser;

import scanner.*;


public class CMinusParser extends Parser {
    private static CMinusScanner scanner;
    public static Token currentToken;
    
    public CMinusParser(String file) {
        scanner = new CMinusScanner(file);
    }
    
    @Override
    public Program parse() {
        currentToken = scanner.getNextToken();
        Program p = new Program();
        return p.parseProgram();
    }
    
    public static String matchToken(Token.TokenType type) {
        if (currentToken.getType() == type) {
            String data = (String)currentToken.getData();
            advanceToken();
            return data;
        }
        throw new CMinusParserException("matchToken: " + type + " does not match " + currentToken.getType());
    }
    
    public static void advanceToken() {
        currentToken = scanner.getNextToken();
    }
    
    public static boolean isInt() {
        return currentToken.getType() == Token.TokenType.INT_TOKEN;
    }
    
    public static boolean isVoid() {
        return currentToken.getType() == Token.TokenType.VOID_TOKEN;
    }
    
    public static void main(String[] args) {
        String filename = "C:\\Users\\kaich\\OneDrive - Cedarville University\\Classes\\Junior\\Spring\\Compiler\\Project\\Compiler\\src\\parser\\testcode";
        String sourceFile = filename + ".c";
        Parser myParser = new CMinusParser (sourceFile);
        Program parseTree = myParser.parse();
        parseTree.printAST();
    }
}
