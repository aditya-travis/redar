package inventory.mgmt.service;

import inventory.core.Inventory;
import inventory.core.InventoryItem;
import inventory.mgmt.core.InventoryMgmtCmd;

import java.util.Map;

/**
 * Created by Feng on 30/8/14.
 */
public class InventoryMgmtServiceImpl implements InventoryMgmtService{

    private AccountingService accountingService = AccountingServiceImpl.getInstance();
    @Override
    public void delete(InventoryMgmtCmd cmd) {
        InventoryItem inventoryItem = Inventory.getInstance().getItem(cmd.getItemName());
        Inventory.getInstance().removeItem(cmd.getItemName());
        accountingService.recordTransaction(cmd, inventoryItem);
    }

    @Override
    public void addNew(InventoryMgmtCmd cmd) {
        InventoryItem inventoryItem = new InventoryItem(cmd.getItemName(), cmd.getBuyAt(), cmd.getSellAt(), 0);
        Inventory.getInstance().newItem(inventoryItem);
    }

    @Override
    public void sell(InventoryMgmtCmd cmd) {
        InventoryItem inventoryItem = Inventory.getInstance().getItem(cmd.getItemName());
        Inventory.getInstance().reduceItem(cmd.getItemName(), cmd.getQuantity());
        accountingService.recordTransaction(cmd, inventoryItem);
    }

    @Override
    public void buy(InventoryMgmtCmd cmd) {
        InventoryItem inventoryItem = Inventory.getInstance().getItem(cmd.getItemName());
        Inventory.getInstance().addItem(cmd.getItemName(), cmd.getQuantity());
        accountingService.recordTransaction(cmd, inventoryItem);
    }
}
