package com.queuesystem.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Component
public class CustomerEntity extends User {

    public CustomerEntity(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.CUSTOMER);
    }
}
