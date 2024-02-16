package com.queuesystem.messageParser;
import com.queuesystem.queue.Task;
import com.queuesystem.request.Order;

/**
 * Command class
 * @author : heracro
 */
public class RequestTaskExecutionMsg implements MessageSender {
    private Order order;
    public RequestTaskExecutionMsg(Task task) {
        this.order = task.getOrderFromDB();
    }
    @Override
    public void send() {
        System.out.println("""
                Message sent:
                dżejson dżejson
                dżejson filepath filepath
                itepe itede
                ino w pojedynczych cudzyslowiach to rób""");

    }
}
