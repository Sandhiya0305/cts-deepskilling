package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }

    @Bean
    public CommandLineRunner displayDate() {
        return args -> {
            LOGGER.info("START");
            ApplicationContext context = new org.springframework.context.support.ClassPathXmlApplicationContext("date-format.xml");
            SimpleDateFormat dateFormat = context.getBean("dateFormat", SimpleDateFormat.class);
            LOGGER.debug("Date format: {}", dateFormat.toPattern());
            try {
                Date date = dateFormat.parse("31/12/2018");
                LOGGER.debug(date.toString());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("END");
        };
    }

    @Bean
    public CommandLineRunner displayCountry() {
        return args -> {
            LOGGER.info("START");
            ApplicationContext context = new org.springframework.context.support.ClassPathXmlApplicationContext("country.xml");
            Country country = context.getBean("country", Country.class);
            LOGGER.debug("Country : {}", country.toString());
            Country anotherCountry = context.getBean("country", Country.class);
            LOGGER.debug("anotherCountry : {}", anotherCountry.toString());
            LOGGER.info("END");
        };
    }

    @Bean
    public CommandLineRunner displayCountries() {
        return args -> {
            LOGGER.info("START");
            ApplicationContext context = new org.springframework.context.support.ClassPathXmlApplicationContext("country.xml");
            List<Country> countries = context.getBean("countryList", List.class);
            LOGGER.debug("Countries : {}", countries);
            LOGGER.info("END");
        };
    }
}