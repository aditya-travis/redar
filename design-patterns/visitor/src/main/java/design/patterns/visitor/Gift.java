package design.patterns.visitor;

/**
 * Created by Feng on 1/9/14.
 */
public class Gift implements Visitable {
    private double netPrice = 20;
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
}
