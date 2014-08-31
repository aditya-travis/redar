package inventory;

import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.service.*;

/**
 * Created by Feng on 31/8/14.
 */
public class CommandSubscribler implements Runnable {

    private CommandBroker cmdBroker;
    private String topicName;

    private InventoryMgmtService inventoryMgmtService = new InventoryMgmtServiceImpl();
    private InventoryReportService inventoryReportService = new InventoryReportServiceImpl();

    private volatile boolean isStop = false;

    public CommandSubscribler(CommandBroker cmdBroker, String topicName) {
        this.cmdBroker = cmdBroker;
        this.topicName = topicName;
    }

    @Override
    public void run() {

        while (!isStop) {

            try {
                InventoryMgmtCmd cmd = this.cmdBroker.take(topicName);

                //Thread.sleep(200);
                switch (cmd.getMgmtType()) {
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
                        break;
                    case STOP:
                        stop();
                }

                System.out.println(Thread.currentThread().getName() + " Consumed " + cmd);

                //Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }

        }

        System.out.println(Thread.currentThread().getName() + " Stopped ");

    }


    public void stop() {

        this.isStop = true;
    }
}
