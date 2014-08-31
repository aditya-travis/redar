package design.patterns.intepreter;

/**
 * Created by Feng on 31/8/14.
 */
public class TenExpression  extends Expression{
    public String one() { return "X"; }
    public String four(){ return "XL"; }
    public String five(){ return "L"; }
    public String nine(){ return "XC"; }
    public int multiplier() { return 10; }
}
