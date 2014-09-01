package design.patterns.vistor;

/**
 * Created by Feng on 1/9/14.
 */
public class Book implements Visitable {
    private double weight=0.5;
    @Override
    public void accept(Vistor vistor) {
        vistor.visit(this);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
