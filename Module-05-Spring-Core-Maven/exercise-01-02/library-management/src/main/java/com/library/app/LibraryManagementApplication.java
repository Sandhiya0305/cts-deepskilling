package com.library.app;

import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookRepository repo = (BookRepository) context.getBean("bookRepository");
        BookService service = (BookService) context.getBean("bookService");

        System.out.println("=== Library Management System ===");
        System.out.println("Books: " + repo.findAll());
        service.display();
    }
}