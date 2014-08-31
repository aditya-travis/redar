package design.patterns.command;

/**
 * Created by Feng on 31/8/14.
 */
public interface Order {

    void execute();

    String getOrderType();
}
