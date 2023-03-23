package com.rabbit;

import com.queue.Queue;
import com.square.Square;

import java.util.ArrayList;
import java.util.List;

public final class App {
    private static String QUEUE_NAME = "square";
    private static String EXCHANGE_NAME = "myExchange";
    private static String KEY_NAME = "key";
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.createExchangeQueue(QUEUE_NAME, EXCHANGE_NAME, "direct", KEY_NAME);
        List numbers = new ArrayList();
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.forEach(n-> queue.sendMessage(EXCHANGE_NAME, KEY_NAME, n.toString()));
        Square sq = new Square();
        sq.listenToMessage();
    }
}