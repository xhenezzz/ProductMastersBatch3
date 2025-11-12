package org.example.HW.Hard;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("visaProcessor")
public class VisaCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплата через VISA: " + amount);
    }
}
