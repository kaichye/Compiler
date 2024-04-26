package parser;

import java.util.ArrayList;
import lowlevel.*;


public class Compound_Statement extends Statement {
    ArrayList<Var_Declaration> local_decl;
    ArrayList<Statement> statement_list;
    
    public Compound_Statement(ArrayList<Var_Declaration> v, ArrayList<Statement> s) {
        local_decl = v;
        statement_list = s;
    }
    
    @Override
    public void print(String indent) {
        for (int i = 0; i < local_decl.size(); i++) {
            local_decl.get(i).print(indent);
        }
        for (int i = 0; i < statement_list.size(); i++) {
            statement_list.get(i).print(indent);
        }
    }
    
    public int getSize() {
        return local_decl.size() + statement_list.size();
    }
    
    @Override
    public void genLLCode(Function func) {
        for (int i = 0; i < local_decl.size(); i++) {
            //FIXME TODO Error when same name exists
            local_decl.get(i).genLLCode(func);
        }
        
        BasicBlock stmts = new BasicBlock(func);
        func.appendToCurrentBlock(stmts);
        func.setCurrBlock(stmts);
        for (int i = 0; i < statement_list.size(); i++) {
            statement_list.get(i).genLLCode(func);
        }
    }
}
