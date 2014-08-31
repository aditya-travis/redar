package design.patterns.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Feng on 31/8/14.
 */
public class ProductFactory {

    private static ProductFactory instance;

    private static Object instanceLock = new Object();

    private Map<String, Product> registry = new ConcurrentHashMap<String, Product>();

    private ProductFactory(){} //private constructor

    public static ProductFactory getInstance(){

        //lazy initialization
        if(instance == null){
            //needs to be initialized
            synchronized (instanceLock){
                instance = new ProductFactory();
                return  instance;
            }
        }

        return instance;
    }

    public Product createProduct(String productType){

        return registry.get(productType).clone();

    }

    public void registerProduct(String productType, Product product){

        registry.put(productType, product);
    }


}
