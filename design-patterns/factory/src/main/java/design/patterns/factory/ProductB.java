package design.patterns.factory;

/**
 * Created by Feng on 31/8/14.
 */
public class ProductB extends Product {
    private String rating;

    @Override
    protected Product clone() {
        ProductB productB = new ProductB();
        productB.rating = this.rating;
        productB.productType = this.productType;

        return productB;
    }

    @Override
    public String toString() {
        return "ProductB{" +
                "rating='" + rating + '\'' +
                '}';
    }
}
