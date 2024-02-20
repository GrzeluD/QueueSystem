package com.queuesystem.dbAdapter;

import com.queuesystem.messageParser.TaskReport;
import com.queuesystem.queue.Task;
import com.queuesystem.request.Order;
import com.queuesystem.request.OrderRepository;
import com.queuesystem.request.Request;
import com.queuesystem.request.RequestRepository;
import com.queuesystem.resources.Resources;
import com.queuesystem.resources.ResourcesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@AllArgsConstructor
public class DBAdapter {
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;
    private final ResourcesRepository resourcesRepository;

    public static Resources getTotalResources() {
        return new Resources(1000, 1000, 4194304);
    }

    public boolean updateFinishedOrder(TaskReport report) {
        Order order = (Order) this.findRequestById(report.getTaskId());

        order.setRequestStatus("Completed");
        requestRepository.save(order);
        //return false only if updating record in DB failed, then
        //report should land in waiting queue and system tries to re-write it
        //upon successful storing of next incoming report.
        return true;
    }

    public static Resources getRequiredResources(Task task) {
        int requestId = task.getId();
        //search in DB and get required cpu, gpu, ram
        // return new Resource(cpu, gpu, ram);
        return new Resources (10, 2, 256);
    }

    public static Order getOrderForTask(Task task) {
        return new Order();
    }

    public void saveRequest(Request request) { requestRepository.save(request); }

    public void saveOrder(Order order) { orderRepository.save(order); }

    public void saveResources(Resources resources) { resourcesRepository.save(resources); }

    public List<Request> findRequestsByUserId(Integer userId) { return requestRepository.findByUserId(userId); }

    public Resources findResourceById() { return resourcesRepository.findById(6).orElseThrow(() -> new IllegalStateException("Nie znaleziono zasobów o takim id")); }

    public List<Request> findOnlyRequests() { return findAllRequests().stream().filter(request -> !(request instanceof Order)).collect(Collectors.toList()); }

    public List<Order> findAllOrders() { return requestRepository.findAll().stream().filter(request -> request instanceof Order).map(request -> (Order) request).collect(Collectors.toList()); }

    public List<Order> findAllApprovedOrders() {return requestRepository.findAll().stream().filter(request -> request instanceof Order && "Approved".equals(((Order) request).getRequestStatus())).map(request -> (Order) request).collect(Collectors.toList());}

    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }

    public Request findRequestById(Integer id) { return requestRepository.findById(id).orElseThrow(() -> new IllegalStateException("Nie można znaleźć requestu o podanym ID"));  }
}
