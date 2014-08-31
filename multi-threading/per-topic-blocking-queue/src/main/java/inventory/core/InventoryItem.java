package inventory.core;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for representing a Inventory Items
 * @author Feng
 *
 */
public class InventoryItem {

	private String itemName;
	private double buyAt;
	private double sellAt;
	private AtomicInteger onHand;
	
	public InventoryItem(String itemName, double buyAt, double sellAt, int onHand) {
		super();
		this.itemName = itemName;
		this.buyAt = buyAt;
		this.sellAt = sellAt;
        this.onHand = new AtomicInteger(onHand);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getBuyAt() {
		return buyAt;
	}

	public void setBuyAt(double buyAt) {
		this.buyAt = buyAt;
	}

	public double getSellAt() {
		return sellAt;
	}

	public void setSellAt(double sellAt) {
		this.sellAt = sellAt;
	}

	public AtomicInteger getOnHand() {
		return onHand;
	}

	public void setOnHand(AtomicInteger onHand) {
		this.onHand = onHand;
	}
	
	
}
