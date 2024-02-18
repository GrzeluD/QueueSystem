package com.queuesystem.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RequestRepository requestRepository;

    public void updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getRequestId())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono Order o ID: " + order.getRequestId()));

        existingOrder.setFilePath(order.getFilePath());
        existingOrder.setRequestStatus(order.getRequestStatus());
        existingOrder.setPriority(order.getPriority());
        existingOrder.setCpu(order.getCpu());
        existingOrder.setGpu(order.getGpu());
        existingOrder.setRam(order.getRam());

        orderRepository.save(existingOrder);
    }

    @Transactional
    public Order convertRequestToOrder(Order order) {
        Request request = requestRepository.findById(order.getRequestId()).orElseThrow(() -> new RuntimeException("Nie znaleziono Requestu o id: " + order.getRequestId()));

        if (order.getRequestStatus() == "Rejected") {
            request.setRequestStatus(order.getRequestStatus());

            requestRepository.save(request);
            return order;
        }

        request.setFilePath(order.getFilePath());
        request.setRequestStatus(order.getRequestStatus());
        request.setPriority(order.getPriority());
        request.setCpu(order.getCpu());
        request.setGpu(order.getGpu());
        request.setRam(order.getRam());

        order.setFilePath(request.getFilePath());
        order.setRequestStatus(request.getRequestStatus());
        order.setRequestedAt(request.getRequestedAt());
        order.setUserId(request.getUserId());
        order.setPriority(request.getPriority());
        order.setCpu(request.getCpu());
        order.setGpu(request.getGpu());
        order.setRam(request.getRam());
        order.setApprovedAt(LocalDateTime.now());

        orderRepository.save(order);
        requestRepository.delete(request);

        return order;
    }
}
