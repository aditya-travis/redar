package design.patterns.command;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Feng on 31/8/14.
 */
public class Agent {

    private List<Order> executedOrders = new CopyOnWriteArrayList<Order>();
    public void placeOrder(Order order){

        order.execute();
        executedOrders.add(order);
    }

    public void retrieveOrderHistory(){
        for(Order order: executedOrders){
            System.out.println(order.getOrderType());
        }
    }
}
