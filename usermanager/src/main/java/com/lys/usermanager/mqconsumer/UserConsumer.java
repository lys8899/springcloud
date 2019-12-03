package com.lys.usermanager.mqconsumer;

import com.lys.usermanager.config.RabbitmqQueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 用户新增消息
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-10-29 15:28
 **/
@Component
public class UserConsumer {

    @Autowired
    private CacheManager cacheManager;


    @RabbitListener(queues = RabbitmqQueueConfig.Q_CLEAR_CACHE)
    public void handleClearCache(Message message, Channel channel) {
        System.out.println("------------" + RabbitmqQueueConfig.Q_CLEAR_CACHE + "------------");
        System.out.println(cacheManager);
        System.out.println(message.getBody().toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = RabbitmqQueueConfig.Q_SEND_EMAIL)
    public void handleChangePassword(Message message, Channel channel) {
        System.out.println("------------" + RabbitmqQueueConfig.Q_SEND_EMAIL + "------------");
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = RabbitmqQueueConfig.Q_USER_LOGOUT)
    public void handleDelete(Message message, Channel channel) {
        System.out.println("------------" + RabbitmqQueueConfig.Q_USER_LOGOUT + "------------");
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
