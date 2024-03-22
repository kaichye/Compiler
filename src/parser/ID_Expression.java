package parser;

public class ID_Expression extends Expression {
    String val;
    
    public ID_Expression (String con) {
        val = con;
    }
    
    @Override
    public void print(String indent) {
        System.out.print(val);
    }
}
