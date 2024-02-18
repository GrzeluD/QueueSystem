package com.queuesystem.dbAdapter;

import com.queuesystem.messageParser.Resource;
import com.queuesystem.messageParser.TaskReport;
import com.queuesystem.queue.Task;
import com.queuesystem.request.Order;
import com.queuesystem.request.Request;
import com.queuesystem.request.RequestRepository;
import com.queuesystem.request.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DBAdapter {
    private final RequestRepository requestRepository;

    public static Resource getTotalResources() {
        return new Resource(1000, 1000, 4194304);
    }

    public static boolean updateFinishedOrder(TaskReport report) {
        //return false only if updating record in DB failed, then
        //report should land in waiting queue and system tries to re-write it
        //upon successful storing of next incoming report.
        return false;
    }

    public static Resource getRequiredResources(Task task) {
        int requestId = task.getId();
        //search in DB and get required cpu, gpu, ram
        // return new Resource(cpu, gpu, ram);
        return new Resource (10, 2, 256);
    }

    public static Order getOrderForTask(Task task) {
        return new Order();
    }

    public void saveRequest(Request request) {
       requestRepository.save(request);
    }

    public List<Request> findRequestsByUserId(Integer userId) {
        return requestRepository.findByUserId(userId);
    }

    public List<Request> findOnlyRequests() { return findAllRequests().stream().filter(request -> !(request instanceof Order)).collect(Collectors.toList()); }

    public List<Order> findAllOrders() { return requestRepository.findAll().stream().filter(request -> request instanceof Order).map(request -> (Order) request).collect(Collectors.toList()); }

    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }
}
