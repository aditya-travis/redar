package inventory.mgmt.service;

import inventory.core.InventoryItem;
import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.core.InventoryMgmtType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Feng on 30/8/14.
 */
public class AccountingServiceImpl implements AccountingService {

    private double profit = 0 ;

    private Object profitLock = new Object();

    private List<AccountingRecord> accountingRecords = new CopyOnWriteArrayList<AccountingRecord>();

    private static AccountingServiceImpl accountingService = new AccountingServiceImpl();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss,SSS");

    private AccountingServiceImpl(){
        //private constructor
    }

    public static AccountingServiceImpl getInstance(){
        return accountingService;
    }
    @Override
    public void recordTransaction(InventoryMgmtCmd cmd, InventoryItem inventoryItem) {


        AccountingRecord accountingRecord = new AccountingRecord(cmd, inventoryItem);

        accountingRecords.add(accountingRecord);

        synchronized (profitLock){
            profit += accountingRecord.getNetProfit();
        }
    }

    @Override
    public double getProfit(){
        return profit;
    }

    @Override
    public void resetProfit(){
        this.profit = 0;
    }

    @Override
    public void accountReport(){

        StringBuffer reportBuffer = new StringBuffer();

        reportBuffer.append("                              TRANSACTION REPORT\n" +
                "Timestamp                  Type       Item Name      Price     Quantity     Cash Flow\n" +
                "---------------------      -----      ---------      ------     -------     ----------\n");
        for(AccountingRecord accountingRecord : accountingRecords){
            reportBuffer.append(String.format("%-21s      %-6s     %-9s     %6.2f      %7d      %9.2f\n",
                    dateFormat.format(accountingRecord.getDate()),
                    accountingRecord.getMgmtType(),
                    accountingRecord.getItemName(),accountingRecord.getPrice() ,
                    accountingRecord.getQuantity(),
                    accountingRecord.getCashflow()));
        }

        reportBuffer.append("------------------------------\n");

        System.out.println(reportBuffer.toString());
    }

    private class AccountingRecord {

        private InventoryMgmtType mgmtType;
        private String itemName;
        private double price;
        private int quantity;
        private double cashflow;
        private Date date;

        private double netProfit;


        AccountingRecord(InventoryMgmtCmd cmd, InventoryItem inventoryItem){
            this.mgmtType = cmd.getMgmtType();
            this.itemName = cmd.getItemName();

            this.quantity = cmd.getMgmtType() == InventoryMgmtType.DELETE ? inventoryItem.getOnHand().intValue() : cmd.getQuantity();
            this.date = new Date();

            if(cmd.getMgmtType() == InventoryMgmtType.BUY){
                this.price = inventoryItem.getBuyAt();
                this.cashflow = -1 * this.quantity * this.price;
                this.netProfit = 0;

            }else if(cmd.getMgmtType() == InventoryMgmtType.SELL){
                this.price = inventoryItem.getSellAt();
                this.cashflow = this.quantity * this.price;

                this.netProfit = this.quantity * (inventoryItem.getSellAt() - inventoryItem.getBuyAt());

            }else if(cmd.getMgmtType() == InventoryMgmtType.DELETE){
                this.price = inventoryItem.getBuyAt();
                this.cashflow = -1 * this.quantity * this.price;
                this.netProfit = -1 * inventoryItem.getBuyAt() * inventoryItem.getOnHand().intValue();
            }

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getCashflow() {
            return cashflow;
        }

        public void setCashflow(double cashflow) {
            this.cashflow = cashflow;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getNetProfit() {
            return netProfit;
        }

        public void setNetProfit(double netProfit) {
            this.netProfit = netProfit;
        }
    }
}
