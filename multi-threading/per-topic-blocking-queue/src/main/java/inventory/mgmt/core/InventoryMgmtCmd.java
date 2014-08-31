package inventory.mgmt.core;

/**
 * class for reprenting the command line input
 * @author Feng
 *
 */
public class InventoryMgmtCmd {
	
	private InventoryMgmtType mgmtType;
	private String itemName;
	private double buyAt;
	private double sellAt;
	private int quantity;
	
	public InventoryMgmtCmd(InventoryMgmtType mgmtType, String itemName,
			double buyAt, double sellAt) {
		super();
		this.mgmtType = mgmtType;
		this.itemName = itemName;
		this.buyAt = buyAt;
		this.sellAt = sellAt;
	}

	public InventoryMgmtCmd(InventoryMgmtType mgmtType, String itemName,
			int quantity) {
		super();
		this.mgmtType = mgmtType;
		this.itemName = itemName;
		this.quantity = quantity;
	}

    public InventoryMgmtCmd(InventoryMgmtType mgmtType, String itemName) {
        this.mgmtType = mgmtType;
        this.itemName = itemName;
    }

    public InventoryMgmtCmd(InventoryMgmtType mgmtType) {
		super();
		this.mgmtType = mgmtType;
	}

	public InventoryMgmtType getMgmtType() {
		return mgmtType;
	}

	public void setMgmtType(InventoryMgmtType mgmtType) {
		this.mgmtType = mgmtType;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InventoryMgmtCmd [mgmtType=" + mgmtType + ", itemName="
				+ itemName + ", buyAt=" + buyAt + ", sellAt=" + sellAt
				+ ", quantity=" + quantity + "]";
	}
	
	
	

}
