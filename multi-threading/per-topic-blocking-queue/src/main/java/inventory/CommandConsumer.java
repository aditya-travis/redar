package inventory;

import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.service.*;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Feng on 31/8/14.
 */
public class CommandConsumer implements Runnable {

    private BlockingQueue<InventoryMgmtCmd> mgmtCmdQueue;

    private InventoryMgmtService inventoryMgmtService = new InventoryMgmtServiceImpl();
    private InventoryReportService inventoryReportService = new InventoryReportServiceImpl();

    public CommandConsumer(BlockingQueue<InventoryMgmtCmd> mgmtCmdQueue) {
        this.mgmtCmdQueue = mgmtCmdQueue;
    }

    @Override
    public void run() {

        while(true){

            try {
                InventoryMgmtCmd cmd = mgmtCmdQueue.take();

                //Thread.sleep(200);
                switch (cmd.getMgmtType()){
                    case BUY:
                        inventoryMgmtService.buy(cmd);
                        break;
                    case SELL:
                        inventoryMgmtService.sell(cmd);
                        break;
                    case NEW:
                        inventoryMgmtService.addNew(cmd);
                        break;
                    case DELETE:
                        inventoryMgmtService.delete(cmd);
                        break;
                    case REPORT:
                        inventoryReportService.report();
                        break;
                    case ACCOUNT:
                        AccountingServiceImpl.getInstance().accountReport();
                }

                //System.out.println(Thread.currentThread().getName() + " Consumed " + cmd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
