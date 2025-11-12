package org.example.HW.Hard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    @Qualifier("visaProcessor")  // ← можно сменить на visaProcessor / bitcoinProcessor / masterProcessor
    private PaymentProcessor paymentProcessor;

    public OrderService() {
        System.out.println("Создался OrderService: " + this);
    }

    public void makeOrder(BigDecimal amount) {
        paymentProcessor.processPayment(amount);
    }
}
