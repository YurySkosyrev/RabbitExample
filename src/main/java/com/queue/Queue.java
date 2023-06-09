package com.queue;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Queue {
    private static final String HOST = "localhost";
    private Channel channel;

    public Queue() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(HOST);
        try {
            Connection connection = cf.newConnection();
            channel = connection.createChannel();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendMessage(String exchange, String key, String message){
        try {
            channel.basicPublish(exchange, key, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void listenToQueue(String queueName, DeliverCallback dlr) {
        try {
            channel.basicConsume(queueName, true, dlr, consumerTag -> { });
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void createExchangeQueue(String queueName, String exchangeName, String exchangeType, String key) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare(exchangeName, exchangeType);
            channel.queueBind(queueName, exchangeName, key);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
