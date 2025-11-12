package org.example.HW.Hard;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DependencyInjectorConfig.class);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.makeOrder(BigDecimal.valueOf(150));

        OrderService orderServiceSecond = context.getBean(OrderService.class);
        orderServiceSecond.makeOrder(BigDecimal.valueOf(300));

        System.out.println("Проверка на Singleton " + (orderService == orderServiceSecond));
    }
}
