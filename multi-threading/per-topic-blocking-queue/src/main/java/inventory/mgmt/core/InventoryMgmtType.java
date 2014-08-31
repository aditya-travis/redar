package inventory.mgmt.core;

public enum InventoryMgmtType {
	NEW, SELL, BUY, DELETE, REPORT, ACCOUNT, STOP;
	
	public static InventoryMgmtType fromString(String typeAsString){
		
		if("new".equalsIgnoreCase(typeAsString)){
			return NEW;
		}else if("sell".equalsIgnoreCase(typeAsString)){
			return SELL;
		}else if("buy".equalsIgnoreCase(typeAsString)){
			return BUY;
		}else if("delete".equalsIgnoreCase(typeAsString)){
			return DELETE;
		}else if("report".equalsIgnoreCase(typeAsString)){
			return REPORT;
		}else if("*".equalsIgnoreCase(typeAsString)){
            return ACCOUNT;
        }
        else{
			throw new RuntimeException( typeAsString + " is not supported");
		}
		
		
	}
}
