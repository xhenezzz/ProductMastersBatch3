package org.example.HW.Easy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Main main = new Main();
        context.getAutowireCapableBeanFactory().autowireBean(main);

        main.greetingService.sayHello();

        context.close();
    }
}