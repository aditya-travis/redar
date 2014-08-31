package design.patterns.singleton;

import java.io.Serializable;

/**
 * Created by Feng on 31/8/14.
 */
public class SingletonClass implements Serializable{

    private static SingletonClass instance = new SingletonClass(); //early loading
    private SingletonClass() {
        //private constructor
    }

    public static SingletonClass getInstance(){
        return instance;
    }

    protected Object readResolve() {
        return getInstance();  //make sure that deserialization are also using the getInstance.
    }

}
