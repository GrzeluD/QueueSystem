package com.queuesystem.user;

import com.queuesystem.request.Request;
import com.queuesystem.request.RequestService;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.queuesystem.security.SecurityUtils.getCurrentUserId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Component
public class Customer extends User {

    public Customer(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.CUSTOMER);
    }

    public Request createRequest() { return new Request(); }

}
