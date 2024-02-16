package com.queuesystem.messageParser;

import com.queuesystem.queue.OrdersQueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageParser {
    private OrdersQueue ordersQueue;
    private Queue<Message> messageQueue;
    private final List<MessageObserver> observers;
    public MessageParser() {
        this.messageQueue = new ConcurrentLinkedQueue<>();
        this.observers = new ArrayList<>();
        registerObserver(new FreeResourceMessageObserver());
        registerObserver(new TaskCompletedObserver());
    }

    public void setOrdersQueue(OrdersQueue ordersQueue) {
        this.ordersQueue = ordersQueue;
    }

    public void registerObserver(MessageObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers(Message message) {
        for (MessageObserver o : observers) {
            o.update(message);
        }
    }
    public void receiveMessage(Message message) {
        notifyObservers(message);
    }
    public void processTaskCompletedMessage(String jsonMessage) {
        try {
            TaskCompletedMsg taskCompletedMsg = JsonParser.parseJson(jsonMessage, TaskCompletedMsg.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
