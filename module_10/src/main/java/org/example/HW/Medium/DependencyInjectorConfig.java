package org.example.HW.Medium;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DependencyInjectorConfig {

    @Bean
    public PaymentProcessor paymentProcessor() {
        //  выбираеv, какой именно процессор будет использоваться
        // return new BitcoinPaymentProcessor();
        return new PlovCoinPaymentProcessor();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public OrderService orderService() {
        return new OrderService();
    }
}
