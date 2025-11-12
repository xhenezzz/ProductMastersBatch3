package org.example.HW.Medium;

import java.math.BigDecimal;

public class BitcoinPaymentProcessor implements PaymentProcessor{

    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплачиваю через Bitcoin кошелек: " + amount);
    }
}
