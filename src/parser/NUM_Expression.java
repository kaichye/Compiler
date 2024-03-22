package parser;

public class NUM_Expression extends Expression {
    String val;
    
    public NUM_Expression (String num) {
        val = num;
    }
    
    public String getVal() {
        return val;
    }
    
    @Override
    public void print(String indent) {
        System.out.print(val);
    }
}
