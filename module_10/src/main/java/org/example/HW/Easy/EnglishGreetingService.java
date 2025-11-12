package org.example.HW.Easy;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}