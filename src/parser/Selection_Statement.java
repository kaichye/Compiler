package parser;


public class Selection_Statement extends Statement {
    Expression exp;
    Statement if_stmt;
    Statement else_stmt;
    
    public Selection_Statement(Expression e, Statement s1, Statement s2) {
        exp = e;
        if_stmt = s1;
        else_stmt = s2;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.println(indent + "if (");
        exp.print(indent + "    ");
        System.out.println(indent + "    " + ")");
        if (if_stmt instanceof Compound_Statement && ((Compound_Statement)if_stmt).getSize() > 1) {
            indent += "    ";
            System.out.println(indent + "{");
        }
        if_stmt.print(indent);
        if (if_stmt instanceof Compound_Statement && ((Compound_Statement)if_stmt).getSize() > 1) {
            System.out.println(indent + "}");
        }
        if (else_stmt != null) {
            System.out.print(indent + "else");
            if (else_stmt instanceof Compound_Statement && ((Compound_Statement)else_stmt).getSize() > 1) {
                System.out.println(" {");
            } else {
                System.out.println("");
            }
            else_stmt.print(indent);
            if (else_stmt instanceof Compound_Statement && ((Compound_Statement)else_stmt).getSize() > 1) {
                System.out.println(indent + "}");
            }
        }
    }
}
