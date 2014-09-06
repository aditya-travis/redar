package design.patterns.chainofresponsibility.handler;

import design.patterns.chainofresponsibility.core.Email;

/**
 * Created by meng on 9/6/14.
 */
public interface EmailHandler {

    public void setNext(EmailHandler emailHandler);

    public void handleRequest(Email email);
}
