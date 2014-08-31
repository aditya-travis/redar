package inventory;

import inventory.core.Inventory;
import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.core.InventoryMgmtType;
import inventory.mgmt.service.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class CommandProducer implements Runnable{

    private BlockingQueue<InventoryMgmtCmd> mgmtCmdQueue;
    private String cmdInputFile;

    public CommandProducer(BlockingQueue<InventoryMgmtCmd> mgmtCmdQueue, String cmdInputFile) {
        this.mgmtCmdQueue = mgmtCmdQueue;
        this.cmdInputFile = cmdInputFile;
    }

    public void produce(String cmdLine) throws Exception {

		String[] cmdArgs = cmdLine.split(" ");

		String cmdType = cmdArgs[0];
		InventoryMgmtType mgmtType = InventoryMgmtType.fromString(cmdType);

		InventoryMgmtCmd cmd;

		switch (mgmtType) {

		case BUY:
		case SELL:
			cmd = new InventoryMgmtCmd(mgmtType, cmdArgs[1],
					Integer.valueOf(cmdArgs[2]));
			break;

		case DELETE:
            cmd = new InventoryMgmtCmd(mgmtType, cmdArgs[1]);
            break;
		case REPORT:
        case ACCOUNT:
			cmd = new InventoryMgmtCmd(mgmtType);
			break;
		case NEW:
			cmd = new InventoryMgmtCmd(mgmtType, cmdArgs[1],
					Double.valueOf(cmdArgs[2]), Double.valueOf(cmdArgs[3]));
			break;
		default:
			throw new RuntimeException( mgmtType + " Unsupported");

		}

        this.mgmtCmdQueue.put(cmd);
        //System.out.println(Thread.currentThread().getName() + " Produced " + cmd);

	}

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.cmdInputFile));

            String cmd;
            while(( cmd = reader.readLine())!=null){
                Thread.sleep(10);
                this.produce(cmd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
