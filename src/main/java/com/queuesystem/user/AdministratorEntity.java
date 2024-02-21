package com.queuesystem.user;

import com.queuesystem.request.Order;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Component
public class AdministratorEntity extends User {

    public AdministratorEntity(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.ADMINISTRATOR);
    }
}