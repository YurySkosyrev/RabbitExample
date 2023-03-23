package com.square;
import com.rabbitmq.client.DeliverCallback;
import com.queue.Queue;

public class Square{
    private static String QUEUE_NAME = "square";
    private static String EXCHANGE_NAME = "myExchange";
    private static String KEY_NAME = "key";
    public void listenToMessage(){
        Queue queue = new Queue();
        queue.createExchangeQueue(QUEUE_NAME, EXCHANGE_NAME, "direct", KEY_NAME);
        queue.listenToQueue(QUEUE_NAME, findSquare);
    }

    DeliverCallback findSquare = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        int number = Integer.parseInt(message);
        int squareNumber = number * number;
        System.out.println("Square of " + message + " is: " + squareNumber );
    };
}