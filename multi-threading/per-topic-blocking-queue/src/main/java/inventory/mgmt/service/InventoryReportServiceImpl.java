package inventory.mgmt.service;

import inventory.core.Inventory;
import inventory.core.InventoryItem;

import java.util.Map;

/**
 * Created by Feng on 30/8/14.
 */
public class InventoryReportServiceImpl implements InventoryReportService {

    private AccountingService accountingService = AccountingServiceImpl.getInstance();
    @Override
    public void report() {

        Map<String,InventoryItem> inventoryRepository = Inventory.getInstance().getInventoryRepository();

        StringBuffer reportBuffer = new StringBuffer();
        reportBuffer.append("                  INVENTORY REPORT\n" +
                "Item Name     Buy At      Sell At      On Hand        Value\n" +
                "---------     ------      -------      -------        -----\n");

        double totalItemVaule = 0;

        double profit= accountingService.getProfit();
        accountingService.resetProfit();
        for(Map.Entry<String, InventoryItem> entry: inventoryRepository.entrySet()){
            InventoryItem item = entry.getValue();
            double currentItemValue = item.getOnHand().doubleValue() * item.getBuyAt();
            reportBuffer.append(String.format("%-9s     %6.2f      %7.2f      %7d      %7.2f\n",
                    item.getItemName(), item.getBuyAt(), item.getSellAt(), item.getOnHand().intValue(), currentItemValue));
            totalItemVaule += currentItemValue;
        }
        reportBuffer.append("------------------------\n");

       reportBuffer.append(String.format("Total value of inventory                           %8.2f\n" +
               "Profit since last report                           %8.2f\n\n",
               totalItemVaule, profit));



 
        System.out.println(reportBuffer.toString());
    }
}
