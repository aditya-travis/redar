package design.patterns.chainofresponsibility.handler;

import design.patterns.chainofresponsibility.core.Email;

/**
 * Created by meng on 9/6/14.
 */
public class BusinessEmailHandler extends AbstractEmailHandler{

    private static final String suffix = "business.com";


    @Override
    public void handleRequest(Email email) {
        if(email.getTo().endsWith(suffix)){
            System.out.println("Sending business email: " + email.getTo());
        }else{
            System.out.println(this.getClass().toString() + " skipping");
        }
        if(next != null){
            next.handleRequest(email);
        }
    }
}
