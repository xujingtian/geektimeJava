package org.redis.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MyListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MyListener.class);


    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        logger.info("接收消息: " +new String(body));
    }
}
