package design.patterns.factory;

/**
 * Created by Feng on 31/8/14.
 */
public class DemoApp {

    static{
        ProductFactory.getInstance().registerProduct("ProductA", new ProductA());
    }

    static{
        ProductFactory.getInstance().registerProduct("ProductB", new ProductB());
    }


    public static void main(String[] args){
        Product productA = ProductFactory.getInstance().createProduct("ProductA");
        System.out.println("Created " + productA);

        Product productB = ProductFactory.getInstance().createProduct("ProductB");
        System.out.println("Created " + productB);
    }
}
