package inventory.mgmt.service;

import inventory.core.InventoryItem;
import inventory.mgmt.core.InventoryMgmtCmd;

/**
 * Created by Feng on 30/8/14.
 */
public interface AccountingService {


    void recordTransaction(InventoryMgmtCmd cmd, InventoryItem inventoryItem);

    double getProfit();

    void resetProfit();

    void accountReport();
}
