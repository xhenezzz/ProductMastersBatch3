package org.example.HW.Hard;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("masterProcessor")
public class MasterCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплата через MasterCard: " + amount);
    }
}
