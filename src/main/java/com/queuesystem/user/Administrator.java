package com.queuesystem.user;

import com.queuesystem.queue.OrdersQueue;
import com.queuesystem.request.Order;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrator extends User {
    public Administrator(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.ADMINISTRATOR);
    }

    public void viewAllRequest() {

    }

    public Order generateOrderFromRequest(Integer requestId) {
        return null;
    }

    public void rejectRequest(Integer requestId) {

    }

    public void viewAllOrders() {

    }


}