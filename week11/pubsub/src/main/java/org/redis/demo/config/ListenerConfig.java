package org.redis.demo.config;

import org.redis.demo.MyListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class ListenerConfig {
    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new MyListener());
    }

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory, MyListener myListener) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //订阅topic - subscribe
        redisMessageListenerContainer.addMessageListener(myListener, new ChannelTopic("subscribe"));
        return redisMessageListenerContainer;
    }
}
