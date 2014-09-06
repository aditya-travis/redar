package design.patterns.chainofresponsibility.processor;

import design.patterns.chainofresponsibility.core.Email;
import design.patterns.chainofresponsibility.handler.EmailHandler;

/**
 * Created by meng on 9/7/14.
 */
public class EmailProcessor {

    private EmailHandler prevHandler; //maintain a reference to the previous end of the chain;
    private EmailHandler firstHandler; //maintain a reference to the first handler

    public void addHandler(EmailHandler handler){

        if(prevHandler != null){
            prevHandler.setNext(handler);
        }else{
            this.firstHandler = handler;
        }

        prevHandler = handler;
    }

    public void handleRequest(Email email){
        firstHandler.handleRequest(email);
    }
}
