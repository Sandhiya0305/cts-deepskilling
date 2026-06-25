package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagement {
    public static void main(String[] args) {
        // Load Spring context to verify all dependencies are available
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("=== Library Management Maven Project ===");
        System.out.println("Spring Context loaded successfully!");
        System.out.println("Spring Version: " + org.springframework.core.SpringVersion.getVersion());
        System.out.println("Dependencies configured:");
        System.out.println("  - spring-context");
        System.out.println("  - spring-aop");
        System.out.println("  - spring-webmvc");
        System.out.println("Java Compiler: 1.8");
    }
}