package com.queuesystem.user;

import com.queuesystem.queue.OrdersQueue;
import com.queuesystem.request.Order;
import com.queuesystem.request.Request;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Administrator extends User {

    @Getter
    private static List<Request> allRequests = new ArrayList<>();

    public Administrator(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.ADMINISTRATOR);
    }

    public static void addRequest(Request request) {
        allRequests.add(request);
    }
}