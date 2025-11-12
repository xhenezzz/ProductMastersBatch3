package org.example.HW.Hard;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("bitcoinProcessor")
public class BitcoinPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплата через Bitcoin: " + amount);
    }
}
