package design.patterns.factory;

/**
 * Created by Feng on 31/8/14.
 */
public abstract  class Product {
    protected String productType;

    protected abstract Product clone();
}
