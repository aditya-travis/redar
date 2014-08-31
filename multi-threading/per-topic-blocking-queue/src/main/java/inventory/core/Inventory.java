package inventory.core;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class to represent the Inventory
 * @author Feng
 *
 */
public class Inventory {
	
	private Map<String, InventoryItem> inventoryRepository = new ConcurrentHashMap<String, InventoryItem>();
	
	private static Inventory inventory = new Inventory();
	private Inventory(){
		//private constructor
	}
	
	public static Inventory getInstance(){
		return inventory;
	}

    public Map<String, InventoryItem> getInventoryRepository() {
        return Collections.unmodifiableMap(new TreeMap<String, InventoryItem>(inventoryRepository));
    }

    public void newItem(InventoryItem inventoryItem) {

        inventoryRepository.put(inventoryItem.getItemName(), inventoryItem);
    }

    public void addItem(String itemName, int quantity) {
        inventoryRepository.get(itemName).getOnHand().addAndGet(quantity);
    }

    public void reduceItem(String itemName, int quantity) {

        inventoryRepository.get(itemName).getOnHand().addAndGet(-1 * quantity);
    }

    public void removeItem(String itemName) {
        inventoryRepository.remove(itemName);
    }

    public InventoryItem getItem(String itemName){
        return inventoryRepository.get(itemName);
    }
}
