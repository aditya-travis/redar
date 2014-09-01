package design.patterns.vistor;

/**
 * Created by Feng on 1/9/14.
 */
public class PostageVistor implements Vistor {

    private double postageCost = 0.0;
    @Override
    public void visit(Visitable visitable) {

        if(visitable instanceof Book){
            postageCost += ((Book)visitable).getWeight() * 2;
        }else if(visitable instanceof Gift){
            postageCost += ((Gift)visitable).getNetPrice() * 0.02;
        }
    }

    public double getPostageCost() {
        return postageCost;
    }
}
