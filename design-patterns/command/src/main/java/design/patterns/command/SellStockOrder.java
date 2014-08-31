package design.patterns.command;

/**
 * Created by Feng on 31/8/14.
 */
public class SellStockOrder implements Order {

    private StockTrade stockTrade;

    public SellStockOrder(StockTrade stockTrade) {
        this.stockTrade = stockTrade;
    }

    @Override
    public void execute() {
        stockTrade.sell();
    }

    @Override
    public String getOrderType() {
        return "SELL";
    }
}
