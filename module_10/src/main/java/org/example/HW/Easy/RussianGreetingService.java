package org.example.HW.Easy;

import org.springframework.stereotype.Component;

@Component
public class RussianGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Привет!");
    }
}