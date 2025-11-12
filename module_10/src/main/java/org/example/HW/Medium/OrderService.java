package org.example.HW.Medium;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;

public class OrderService {

    @Autowired
    private PaymentProcessor paymentProcessor;

    public OrderService() {
        System.out.println("Создался OrderService: " + this);
    }

    public void makeOrder(BigDecimal amount) {
        paymentProcessor.processPayment(amount);
    }
}
