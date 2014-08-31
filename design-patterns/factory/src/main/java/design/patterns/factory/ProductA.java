package design.patterns.factory;

/**
 * Created by Feng on 31/8/14.
 */
public class ProductA extends Product {
    private String producer;
    @Override
    protected Product clone() {
        ProductA productA = new ProductA();
        productA.producer = this.producer;
        productA.productType = this.productType;

        return productA;
    }

    @Override
    public String toString() {
        return "ProductA{" +
                "producer='" + producer + '\'' +
                '}';
    }
}
