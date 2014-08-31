package inventory;

import inventory.mgmt.core.InventoryMgmtCmd;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class InventoryMgmtApp {


	public static void main(String[] args) throws Exception{
		
		String cmdInputFile = "/Users/Feng/Dropbox/Lecture/Java/workspace/inventory-management/input/input1.txt";
//        BlockingQueue<InventoryMgmtCmd> mgmtCmdQueue = new LinkedBlockingDeque<InventoryMgmtCmd>();

//        for(int i=0; i<=2; i++){
//            new Thread(new CommandConsumer(mgmtCmdQueue), "Consumer-" + (i+1)).start();
//        }
//
//        new Thread(new CommandProducer(mgmtCmdQueue, cmdInputFile), "Producer-1").start();

        CommandBroker commandBroker = new CommandBroker();

        new Thread(new CommandPublisher(commandBroker, cmdInputFile), "Publisher-1").start();


	}
}
