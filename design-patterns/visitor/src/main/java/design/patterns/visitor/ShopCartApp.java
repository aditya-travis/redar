package design.patterns.visitor;

/**
 * Created by Feng on 1/9/14.
 */
public class ShopCartApp {

    public static void main(String[] args){

        System.out.println("Total Postage Cost: " + new ShoppingCart().addItem(new Book())
                .addItem(new Gift())
                .addItem(new Book())
                .addItem(new Gift()).caculatePostageCost());
    }
}
