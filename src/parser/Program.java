package parser;

import java.util.ArrayList;
import scanner.*;


public class Program {
    ArrayList<Declaration> decl_list = new ArrayList<>();
    
    public Program parseProgram() {
        while (CMinusParser.currentToken.getType() != Token.TokenType.EOF_TOKEN) {
            if (CMinusParser.isInt() || CMinusParser.isVoid()) {
                decl_list.add(Declaration.parseDeclaration());
            } else {
                throw new CMinusParserException("parseProgram: " + CMinusParser.currentToken.getType() + " is not INT or VOID");
            }
        }
        return this;
    }
    
    public void printAST() {
        String indent = "";
        System.out.println("Program {");
        for (int i = 0; i < decl_list.size(); i++) {
            decl_list.get(i).print(indent);
        }
        System.out.println("}");
    } 
}
