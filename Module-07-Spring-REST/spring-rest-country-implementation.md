# Project 1: spring-rest-country Implementation

## Overview

Country REST API demonstrating GET, POST, Validation, Exception Handling, and MockMVC Tests.

**Covers Exercises:** 2.1 - 2.8, 4.1 - 4.4

**Package:** `com.cognizant.springlearn`

---

## Project Structure

```
spring-rest-country/
├── pom.xml
├── src/main/java/com/cognizant/springlearn/
│   ├── SpringLearnApplication.java
│   ├── model/
│   │   └── Country.java
│   ├── service/
│   │   └── CountryService.java
│   ├── controller/
│   │   ├── HelloController.java
│   │   └── CountryController.java
│   └── exception/
│       ├── CountryNotFoundException.java
│       └── GlobalExceptionHandler.java
├── src/main/resources/
│   ├── application.properties
│   └── country.xml
└── src/test/java/com/cognizant/springlearn/
    └── SpringLearnApplicationTests.java
```

---

## Step 1: Project Setup

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <groupId>com.cognizant</groupId>
    <artifactId>spring-rest-country</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-rest-country</name>
    <description>Country REST API - Exercises 2.1-2.8, 4.1-4.4</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### src/main/resources/application.properties

```properties
logging.level.org.springframework=info
logging.level.com.cognizant.springlearn=debug
logging.pattern.console=%d{yyMMdd}|%d{HH:mm:ss.SSS}|%-20.20thread|%5p|%-25.25logger{25}|%25M|%m%n
server.port=8080
```

---

## Step 2: Model - Country.java

**Exercise 2.2** (basic POJO) + **Exercise 4.3** (validation)

```java
package com.cognizant.springlearn.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Country {

    @NotNull
    @Size(min = 2, max = 2, message = "Country code should be 2 characters")
    private String code;

    private String name;

    public Country() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
```

---

## Step 3: Spring XML Configuration - country.xml

**Exercises 2.2 + 2.3**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="in" class="com.cognizant.springlearn.model.Country">
        <property name="code" value="IN" />
        <property name="name" value="India" />
    </bean>

    <bean id="us" class="com.cognizant.springlearn.model.Country">
        <property name="code" value="US" />
        <property name="name" value="United States" />
    </bean>

    <bean id="de" class="com.cognizant.springlearn.model.Country">
        <property name="code" value="DE" />
        <property name="name" value="Germany" />
    </bean>

    <bean id="jp" class="com.cognizant.springlearn.model.Country">
        <property name="code" value="JP" />
        <property name="name" value="Japan" />
    </bean>

    <bean id="countryList" class="java.util.ArrayList">
        <constructor-arg>
            <list>
                <ref bean="in" />
                <ref bean="us" />
                <ref bean="de" />
                <ref bean="jp" />
            </list>
        </constructor-arg>
    </bean>

</beans>
```

---

## Step 4: Service - CountryService.java

**Exercises 2.2 - 2.5**

```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    public Country getCountryIndia() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("in", Country.class);
        LOGGER.info("END");
        return country;
    }

    public List<Country> getAllCountries() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.info("END");
        return countries;
    }

    public Country getCountry(String code) {
        LOGGER.info("START");
        List<Country> countries = getAllCountries();
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));
        LOGGER.info("END");
        return country;
    }
}
```

---

## Step 5: Hello Controller - HelloController.java

**Exercise 2.1**

```java
package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start");
        LOGGER.info("End");
        return "Hello World!!";
    }
}
```

**Test:** `curl -s http://localhost:8080/hello`

---

## Step 6: Country Controller - CountryController.java

**Exercises 2.2 - 2.5 + 4.1**

```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start");
        Country country = countryService.getCountryIndia();
        LOGGER.info("End");
        return country;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("End");
        return countries;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End");
        return country;
    }
}
```

---

## Step 7: Exception Handling

### CountryNotFoundException.java

**Exercise 2.5**

```java
package com.cognizant.springlearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String message) {
        super(message);
    }
}
```

### GlobalExceptionHandler.java

**Exercise 4.4**

```java
package com.cognizant.springlearn.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOGGER.info("Start");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        LOGGER.info("End");
        return new ResponseEntity<>(body, headers, status);
    }
}
```

---

## Step 8: Main Application - SpringLearnApplication.java

```java
package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }
}
```

---

## Step 9: MockMVC Tests

**Exercises 2.6 - 2.8**

```java
package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    @Test
    void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].code").value("IN"));
        actions.andExpect(jsonPath("$[1].code").value("US"));
        actions.andExpect(jsonPath("$[2].code").value("DE"));
        actions.andExpect(jsonPath("$[3].code").value("JP"));
    }

    @Test
    void testGetCountryByCode() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/in"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound());
    }
}
```

---

## Testing Commands

### GET Endpoints

```bash
# Hello World
curl -s http://localhost:8080/hello

# Get India
curl -s http://localhost:8080/country

# Get all countries
curl -s http://localhost:8080/countries

# Get country by code (case-insensitive)
curl -s http://localhost:8080/countries/in
curl -s http://localhost:8080/countries/US

# Get non-existent country (returns 404)
curl -s http://localhost:8080/countries/az
```

### POST Endpoint

```bash
# Add a new country
curl -i -H 'Content-Type: application/json' -X POST \
  -d '{"code":"CA","name":"Canada"}' \
  http://localhost:8080/countries

# Validation error - code too short
curl -i -H 'Content-Type: application/json' -X POST \
  -d '{"code":"I","name":"India"}' \
  http://localhost:8080/countries
```

### Run Tests

```bash
# Run all tests
mvn clean test

# Run specific test
mvn test -Dtest=SpringLearnApplicationTests#testGetCountry
```

---

## Run Application

```bash
cd Module-07-Spring-REST\spring-rest-country
mvn spring-boot:run
```
