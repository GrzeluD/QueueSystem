package com.queuesystem.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceManEntity extends User {
    public ServiceManEntity(String name, String username, String email, String password) {
        super(name, username, email, password);
        setUserRole(UserRole.SERVICEMAN);
    }
}
