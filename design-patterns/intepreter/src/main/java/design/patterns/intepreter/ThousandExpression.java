package design.patterns.intepreter;

/**
 * Created by Feng on 31/8/14.
 */
public class ThousandExpression  extends Expression{

    public String one() { return "M"; }
    public String four(){ return " "; }
    public String five(){ return " "; }
    public String nine(){ return " "; }
    public int multiplier() { return 1000; }
}