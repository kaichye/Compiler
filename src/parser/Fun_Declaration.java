package parser;

import java.util.ArrayList;
import scanner.Token;
import lowlevel.*;


public class Fun_Declaration extends Declaration {
    Token.TokenType type;
    ID_Expression val;
    ArrayList<Param> params;
    Compound_Statement cs;
    
    public Fun_Declaration(Token.TokenType t, ID_Expression s, ArrayList<Param> p, Compound_Statement c) {
        type = t;
        val = s;
        params = p;
        cs = c;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        if (type == Token.TokenType.INT_TOKEN) {
            System.out.print(indent + "int ");
            val.print(indent);
            System.out.println(" (");
        } else if (type == Token.TokenType.VOID_TOKEN) {
            System.out.print(indent + "void ");
            val.print(indent); 
            System.out.println(" (");
        }
        indent += "    ";
        for (int i = 0; i < params.size(); i++) {
            params.get(i).print(indent);
        }
        System.out.println(indent + ")");
        
        // print compound statement
        System.out.println(indent + "{");
        cs.print(indent);
        System.out.println(indent + "}");
    }
    
    public void genLLCode(CodeItem temp) {
        Function func = (Function)temp;
        
        FuncParam head = null;
        FuncParam prev = null;
        for (int i = 0; i < params.size(); i++) {
            //FIXME TODO Error when same name exists
            if (i == 0) {
                if (params.get(i).type == Token.TokenType.VOID_TOKEN) {
                    break;
                }
                head = new FuncParam(1, ((ID_Expression)params.get(i).var).val);
                prev = head;
            } else {
                FuncParam tmp = new FuncParam(1, ((ID_Expression)params.get(i).var).val);
                prev.setNextParam(tmp);
                prev = tmp;
            }
            
            func.getTable().put(prev.getName(), func.getNewRegNum());
        }
        func.setFirstParam(head);
        
        func.createBlock0();
        func.setCurrBlock(func.getFirstBlock());
        
        cs.genLLCode(func);
        
        func.genReturnBlock();
        func.appendBlock(func.getReturnBlock());
        if (func.getFirstUnconnectedBlock() != null) {
            func.appendBlock(func.getFirstUnconnectedBlock());
        }
    }
    
    public int getType() {
        if (this.type == Token.TokenType.VOID_TOKEN) {
            return 0;
        } else {
            return 1;
        }
    }
}
