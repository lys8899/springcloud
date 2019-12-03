package com.lys.usermanager.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description: rabbitMq 配置类
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-10-29 15:06
 **/
@Configuration
public class RabbitmqQueueConfig {
    /**
     * 定义序列化格式
     *
     * @return
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定义的队列
     * Queue 可以有4个参数
     * 1.队列名
     * 2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
     * 3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
     * 4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
     */
    public final static String Q_CLEAR_CACHE = "clearCache";
    public final static String Q_SEND_EMAIL = "sendEmail";
    public final static String Q_USER_LOGOUT = "userLogout";

    @Bean
    public Queue sendEmail() {
        return new Queue(Q_SEND_EMAIL);
    }

    @Bean
    public Queue userLogout() {
        return new Queue(Q_USER_LOGOUT);
    }

    @Bean
    public Queue clearCache() {
        return new Queue(Q_CLEAR_CACHE);
    }


    /**
     * Exchanges
     */
    public final static String E_CHANGE_PASSWOR = "changePassword";
    public final static String E_DELETE_USER = "deleteUser";

    @Bean
    public FanoutExchange changePassword() {
        return new FanoutExchange(E_CHANGE_PASSWOR);
    }

    @Bean
    public FanoutExchange deleteUser() {
        return new FanoutExchange(E_DELETE_USER);
    }


    /**
     * Binding
     *
     * @return
     */
    @Bean
    public Binding changePasswordBind1() {
        return BindingBuilder.bind(sendEmail()).to(changePassword());
    }

    @Bean
    public Binding changePasswordBind2() {
        return BindingBuilder.bind(clearCache()).to(changePassword());
    }

    @Bean
    public Binding changePasswordBind3() {
        return BindingBuilder.bind(userLogout()).to(changePassword());
    }

    @Bean
    public Binding addUserBind1() {
        return BindingBuilder.bind(sendEmail()).to(deleteUser());
    }

    @Bean
    public Binding addUserBind2() {
        return BindingBuilder.bind(userLogout()).to(deleteUser());
    }

    @Bean
    public Binding addUserBind3() {
        return BindingBuilder.bind(clearCache()).to(deleteUser());
    }

}
