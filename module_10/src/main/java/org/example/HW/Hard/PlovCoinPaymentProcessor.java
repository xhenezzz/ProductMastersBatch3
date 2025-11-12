package org.example.HW.Hard;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("plovCoinProcessor")
public class PlovCoinPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплата через PlovCoin: " + amount);
    }
}
