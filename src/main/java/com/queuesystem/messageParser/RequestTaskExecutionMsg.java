package com.queuesystem.messageParser;
import com.queuesystem.queue.Task;

/**
 * Command class
 * @author : heracro
 */
public class RequestTaskExecutionMsg implements MessageSender {
    private Task task;
    public RequestTaskExecutionMsg(Task task) {
        this.task = task;
    }
    @Override
    public void send() {
        System.out.println("Message sent:");

    }
}
