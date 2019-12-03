package com.lys.usermanager.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 * @description: rabbitMq 配置类
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-10-29 15:06
 **/
@Configuration
public class RabbitTemplateConfig {
    private final Logger logger = LoggerFactory.getLogger(RabbitTemplateConfig.class);

/** ======================== 定制一些处理策略 =============================*/
    /**
     * 定制化amqp模版
     * <p>
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {

        //消息发送失败返回到队列中, 需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}",
                    correlationId, replyCode, replyText, exchange, routingKey);
        });

        //消息确认, 配置 spring.rabbitmq.publisher-confirm-type
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.info("消息发送到exchange成功,id: {}", correlationData);
            } else {
                logger.info("消息发送到exchange失败,原因: {}", cause);
            }
        });
    }

}
