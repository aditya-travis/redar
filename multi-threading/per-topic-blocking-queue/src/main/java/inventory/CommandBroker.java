package inventory;

import inventory.mgmt.core.InventoryMgmtCmd;
import inventory.mgmt.core.InventoryMgmtType;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Feng on 31/8/14.
 */
public class CommandBroker {

    private Map<String, LinkedBlockingQueue<InventoryMgmtCmd>> topics = new ConcurrentHashMap<String, LinkedBlockingQueue<InventoryMgmtCmd>>();
    private Map<String, CommandSubscribler> topicSubscribers = new ConcurrentHashMap<String, CommandSubscribler>();

    /**
     * Method used for publisher to publish / add cmds
     * @param itemName
     * @param cmd
     * @throws Exception
     */
    public void add(String itemName, InventoryMgmtCmd cmd) throws Exception{

        String topicName =  null == itemName ? "admin" : itemName;

        if(topics.containsKey(topicName)){
            topics.get(topicName).put(cmd);
        }else{
            LinkedBlockingQueue<InventoryMgmtCmd> topicQueue = new LinkedBlockingQueue<InventoryMgmtCmd>();
            topicQueue.put(cmd);
            topics.put(topicName, topicQueue);
            //pass the topic to a new created subscriber
            CommandSubscribler commandSubscribler = new CommandSubscribler(this, topicName);
            new Thread(commandSubscribler, "Consumer-" + topicName).start();
            topicSubscribers.put(topicName, commandSubscribler);

        }
    }

    /**
     * Method used for Consumer to retrieve any cmd from a given topic
     * @param topicName
     * @return
     * @throws Exception
     */
    public InventoryMgmtCmd take(String topicName) throws Exception{


        return this.topics.get(topicName).take();
    }

    /**
     * Method used for the publisher to signal done
     */
    public void done() throws Exception{

        for(Map.Entry<String, LinkedBlockingQueue<InventoryMgmtCmd>> entry: topics.entrySet()){
            entry.getValue().put(new InventoryMgmtCmd(InventoryMgmtType.STOP));
        }
    }

    private void stop() {
        for(Map.Entry<String, CommandSubscribler> entry: topicSubscribers.entrySet()){
            entry.getValue().stop();
        }
    }
}
