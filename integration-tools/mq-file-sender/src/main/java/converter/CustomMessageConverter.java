package converter;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by meng on 6/14/14.
 */
public class CustomMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        return (Message)object;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return message;
    }
}
