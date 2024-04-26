package parser;

import java.util.ArrayList;
import scanner.*;
import lowlevel.*;


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
    
    public CodeItem genLLCode() {
        CodeItem head = null;
        CodeItem prev = null;
        
        for (int i = 0; i < decl_list.size(); i++) {
            Declaration d = decl_list.get(i);
            if (i == 0) {
                if (d instanceof Var_Declaration) {
                    head = new Data(1, ((Var_Declaration) d).var.val);
                    ((Var_Declaration)d).genLLCode(head);
                } else if (d instanceof Fun_Declaration) {
                    head = new Function(((Fun_Declaration) d).getType(), ((Fun_Declaration) d).val.val);
                    ((Fun_Declaration)d).genLLCode(head);
                }
                prev = head;
            } else {
                CodeItem temp = null;
                if (d instanceof Var_Declaration) {
                    temp = new Data(1, ((Var_Declaration) d).var.val);
                    ((Var_Declaration)d).genLLCode(temp);
                } else if (d instanceof Fun_Declaration) {
                    temp = new Function(((Fun_Declaration) d).getType(), ((Fun_Declaration) d).val.val);
                    ((Fun_Declaration)d).genLLCode(temp);
                }
                prev.setNextItem(temp);
                prev = temp;
            }
            decl_list.get(i);
        }
        
        return head;
    }
}
