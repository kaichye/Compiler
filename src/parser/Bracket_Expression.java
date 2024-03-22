package parser;

public class Bracket_Expression extends Expression{
    Expression ID;
    Expression arrayNum;
   
    public Bracket_Expression(Expression val, Expression arrayExp){
        ID = val;
        arrayNum = arrayExp;
    }
    
    @Override
    public void print(String indent) {
        indent += "    ";
        System.out.print(indent);
        ID.print("");
        System.out.print("["); 
        arrayNum.print("");
        System.out.println("]");
    }
}
