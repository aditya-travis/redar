package design.patterns.command;

/**
 * Created by Feng on 31/8/14.
 */
public class ClientDemo {

    public static void main(String[] args){
        Agent agent = new Agent();
        StockTrade stockTrade = new StockTrade();
        Order order1 = new BuyStockOrder(stockTrade);
        Order order2 = new BuyStockOrder(stockTrade);
        Order order3 = new SellStockOrder(stockTrade);

        agent.placeOrder(order1);
        agent.placeOrder(order2);
        agent.placeOrder(order3);

        agent.retrieveOrderHistory();
    }
}
