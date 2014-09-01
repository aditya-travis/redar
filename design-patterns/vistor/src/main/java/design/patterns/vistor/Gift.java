package design.patterns.vistor;

/**
 * Created by Feng on 1/9/14.
 */
public class Gift implements Visitable {
    private double netPrice = 20;
    @Override
    public void accept(Vistor vistor) {
        vistor.visit(this);
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
}
