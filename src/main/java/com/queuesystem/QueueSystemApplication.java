package com.queuesystem;

import com.queuesystem.messageParser.MessageParser;
import com.queuesystem.queue.OrdersQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueueSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(QueueSystemApplication.class, args);
		MessageParser messageParser = new MessageParser();
		OrdersQueue ordersQueue = new OrdersQueue();
	}
}
