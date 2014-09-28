package design.patterns.visitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Feng on 1/9/14.
 */
public class ShoppingCart {

    private List<Visitable> shopItems = new CopyOnWriteArrayList<Visitable>();

    private PostageVisitor postageVisitor = new PostageVisitor();

    public ShoppingCart addItem(Visitable item){
        shopItems.add(item);
        return this;
    }

    public double caculatePostageCost(){
        for(Visitable item : shopItems){
            postageVisitor.visit(item);
        }

        return postageVisitor.getPostageCost();
    }
}
