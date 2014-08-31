package inventory.mgmt.service;

import inventory.mgmt.core.InventoryMgmtCmd;

public interface InventoryMgmtService {

    void delete(InventoryMgmtCmd cmd);

    void addNew(InventoryMgmtCmd cmd);

    void sell(InventoryMgmtCmd cmd);

    void buy(InventoryMgmtCmd cmd);
}
