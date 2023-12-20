package com.epam.gymcrm;


import com.epam.gymcrm.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new AnnotationConfigApplicationContext("com.epam.gymcrm");

        // Retrieve a bean from the context
        MyService myService = context.getBean(MyService.class);

        // Use the bean's methods
        myService.doSomething();



//        or
//
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.package");
//
//        or
//
//        ApplicationContext context = new GenericGroovyApplicationContext("config.groovy");

        // retrieve configured instance
        //MyService myService = context.getBean(MyService.class);

        // use configured instance
        //myService.doWork();

        ((ConfigurableApplicationContext)context).close();



    }
}