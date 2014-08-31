package design.patterns.command;

/**
 * Created by Feng on 31/8/14.
 */
public class BuyStockOrder implements Order {

    private StockTrade stockTrade;

    public BuyStockOrder(StockTrade stockTrade) {
        this.stockTrade = stockTrade;
    }

    @Override
    public void execute() {
        stockTrade.buy();
    }

    @Override
    public String getOrderType() {
        return "BUY";
    }
}
