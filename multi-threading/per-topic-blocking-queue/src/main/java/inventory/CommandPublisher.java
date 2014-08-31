package inventory;

import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.core.InventoryMgmtType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Feng on 31/8/14.
 */
public class CommandPublisher implements Runnable{

    private String cmdInputFile;

    private CommandBroker cmdBroker;

    public CommandPublisher(CommandBroker cmdBroker, String cmdInputFile) {
        this.cmdBroker = cmdBroker;
        this.cmdInputFile = cmdInputFile;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.cmdInputFile));

            String cmd;
            while(( cmd = reader.readLine())!=null){
                Thread.sleep(5);
                this.publish(cmd);

            }

            cmdBroker.done();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(String cmdLine) throws Exception{

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

        this.cmdBroker.add(cmd.getItemName(), cmd);

    }
}
