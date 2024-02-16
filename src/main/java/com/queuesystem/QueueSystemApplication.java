package com.queuesystem;

import com.queuesystem.messageParser.*;
import com.queuesystem.queue.OrdersQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class QueueSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(QueueSystemApplication.class, args);


		MessageParser messageParser = new MessageParser();
		OrdersQueue ordersQueue = new OrdersQueue();
		messageParser.setOrdersQueue(ordersQueue);

		//Simulation of new message from SuperComputer:
		String taskCompletedJson = "{\"taskId\":\"123\",\"startTime\":\"2023-01-01T12:00:00\",\"endTime\":\"2023-01-01T12:30:00\",\"resultFolderPath\":\"/results/task123\",\"resultFolderRemovalDate\":\"2023-06-01T00:00:00\"}";
		String freeResourcesReportJson = "{\"freeCPUs\":4,\"freeGPUs\":2,\"freeMemory\":8192}";
//		try {
//			messageParser.receiveMessage(JsonParser.parseFreeResourcesReportMsg(freeResourcesReportJson));
//			messageParser.receiveMessage(JsonParser.parseTaskCompletedMsg(taskCompletedJson));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
