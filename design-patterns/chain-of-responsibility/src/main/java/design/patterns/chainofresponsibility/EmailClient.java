package design.patterns.chainofresponsibility;

import design.patterns.chainofresponsibility.core.Email;
import design.patterns.chainofresponsibility.handler.BusinessEmailHandler;
import design.patterns.chainofresponsibility.handler.EmailHandler;
import design.patterns.chainofresponsibility.handler.GmailEmailHandler;
import design.patterns.chainofresponsibility.processor.EmailProcessor;

/**
 * Created by meng on 9/7/14.
 */
public class EmailClient {

    private EmailProcessor emailProcessor;

    public EmailClient() {

        createProcessor();
    }

    private void createProcessor(){

        emailProcessor = new EmailProcessor();
        emailProcessor.addHandler(new BusinessEmailHandler());
        emailProcessor.addHandler(new GmailEmailHandler());
    }

    public void addRule(EmailHandler emailHandler){
        emailProcessor.addHandler(emailHandler);
    }

    public void sentEmail(Email email){
        emailProcessor.handleRequest(email);
    }

    public static void  main(String[] args){

        EmailClient emailClient = new EmailClient();
        emailClient.sentEmail(new Email() {{
            setTo("wubi@gmail.com");
        }});

        emailClient.sentEmail(new Email() {{
            setTo("biz@business.com");
        }});
    }
}
