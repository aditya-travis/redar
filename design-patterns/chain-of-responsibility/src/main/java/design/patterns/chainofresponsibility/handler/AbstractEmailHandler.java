package design.patterns.chainofresponsibility.handler;

import design.patterns.chainofresponsibility.core.Email;

/**
 * Created by meng on 9/7/14.
 */
public  abstract  class AbstractEmailHandler implements EmailHandler {
    protected EmailHandler next;
    @Override
    public void setNext(EmailHandler emailHandler) {
        next = emailHandler;
    }

    @Override
    public abstract void handleRequest(Email email);
}
