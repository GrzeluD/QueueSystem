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
	}
}
