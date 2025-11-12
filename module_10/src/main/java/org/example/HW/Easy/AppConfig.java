package org.example.HW.Easy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GreetingService greetingService() {
        return new RussianGreetingService();
//  Для Английского
//      return new EnglishGreetingService();
    }
}