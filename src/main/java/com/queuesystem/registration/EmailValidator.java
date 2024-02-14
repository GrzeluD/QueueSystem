package com.queuesystem.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    //TODO create regex to validate email
    @Override
    public boolean test(String s) {
        return true;
    }
}
