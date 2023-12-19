package com.epam.gymcrm;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

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
    }
}