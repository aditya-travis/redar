package org.zths.samples.router;

import org.springframework.integration.router.AbstractMappingMessageRouter;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.*;

/**
 * Created by Feng on 24/5/14.
 */
public class ContentBasedRouter extends AbstractMappingMessageRouter {
    @Override
    protected List<Object> getChannelKeys(Message<?> message) {
        String content = (String)message.getPayload();

        for(Map.Entry<String, String> entry : this.getChannelMappings().entrySet()){
            if(content.contains((String) entry.getKey())){
                return Collections.<Object>singletonList(entry.getKey());
            }
        }

        throw new RuntimeException("Not able to find the mataching channel");
    }

}
