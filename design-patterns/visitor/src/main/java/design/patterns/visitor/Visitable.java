package design.patterns.visitor;

/**
 * Created by Feng on 1/9/14.
 */
public interface Visitable {

    void accept(Visitor visitor);
}
