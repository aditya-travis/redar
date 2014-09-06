package design.patterns.chainofresponsibility.handler;

import design.patterns.chainofresponsibility.core.Email;

/**
 * Created by meng on 9/7/14.
 */
public class GmailEmailHandler extends AbstractEmailHandler {

    private static final String suffix = "gmail.com";
    @Override
    public void handleRequest(Email email) {
        if(email.getTo().endsWith(suffix)) {
            System.out.println("Sending gmail message: " + email.getTo());
        }else{
            System.out.println(this.getClass().toString() + " skipping");
        }

        if(next != null){
            next.handleRequest(email);
        }
    }
}
