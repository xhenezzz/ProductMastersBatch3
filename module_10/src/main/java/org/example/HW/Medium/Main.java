package org.example.HW.Medium;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DependencyInjectorConfig.class);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.makeOrder(BigDecimal.valueOf(15));

        System.out.println("--------------------");

        PaymentProcessor btc = new BitcoinPaymentProcessor();
        btc.processPayment(BigDecimal.valueOf(0.01));

        PaymentProcessor plov = new PlovCoinPaymentProcessor();
        plov.processPayment(BigDecimal.valueOf(1000));
    }
}
