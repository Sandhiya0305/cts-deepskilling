# Spring REST Hands-On Implementation Guide

## Overview

This document contains all Spring REST exercises extracted from the training materials, organized into **2 projects**:

| Project | Focus | Exercises |
|---------|-------|-----------|
| `spring-rest-country` | Country REST API (GET + POST + Validation + MockMVC) | 2.1 - 2.8, 4.1 - 4.4 |
| `spring-rest-employee` | Employee REST API (Full CRUD + Department + Validation) | 3.1 - 3.5, 4.5 - 4.8 |

---

## Project 1: spring-rest-country

### Exercise 2.1: Hello World REST

**Task:** Create a REST service at `/hello` that returns `"Hello World!!"`

**Implementation:**

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

**Test:**
- Browser: `http://localhost:8080/hello`
- Postman: GET `http://localhost:8080/hello`
- curl: `curl -s http://localhost:8080/hello`

---

### Exercise 2.2: Get Country REST

**Task:** Create `/country` endpoint that loads India bean from Spring XML and returns JSON

**Files to Create:**

**`src/main/resources/country.xml`**
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

</beans>
```

**`src/main/java/com/cognizant/springlearn/model/Country.java`**
```java
package com.cognizant.springlearn.model;

public class Country {
    private String code;
    private String name;

    public Country() {}

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

**`src/main/java/com/cognizant/springlearn/service/CountryService.java`**
```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    public Country getCountryIndia() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("in", Country.class);
    }
}
```

**`src/main/java/com/cognizant/springlearn/controller/CountryController.java`**
```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
```

**Test:** GET `http://localhost:8080/country`
**Expected Response:**
```json
{
  "code": "IN",
  "name": "India"
}
```

---

### Exercise 2.3: Get All Countries

**Task:** Create `/countries` endpoint returning all countries as JSON array

**Update `country.xml`:**
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

**Update `CountryService.java`:**
```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public Country getCountryIndia() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("in", Country.class);
    }

    public List<Country> getAllCountries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("countryList", List.class);
    }
}
```

**Update `CountryController.java`:**
```java
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("End");
        return countries;
    }
```

**Test:** GET `http://localhost:8080/countries`
**Expected Response:**
```json
[
  {"code": "IN", "name": "India"},
  {"code": "US", "name": "United States"},
  {"code": "DE", "name": "Germany"},
  {"code": "JP", "name": "Japan"}
]
```

---

### Exercise 2.4: Get Country by Code

**Task:** Create `/countries/{code}` endpoint with case-insensitive search

**Update `CountryService.java`:**
```java
    public Country getCountry(String code) {
        List<Country> countries = getAllCountries();
        return countries.stream()
            .filter(c -> c.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElse(null);
    }
```

**Update `CountryController.java`:**
```java
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }
```

**Test:** GET `http://localhost:8080/countries/in`
**Expected Response:**
```json
{"code": "IN", "name": "India"}
```

---

### Exercise 2.5: Country Not Found Exception

**Task:** Throw 404 when country code doesn't exist

**`src/main/java/com/cognizant/springlearn/exception/CountryNotFoundException.java`**
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

**Update `CountryService.java`:**
```java
    public Country getCountry(String code) {
        List<Country> countries = getAllCountries();
        return countries.stream()
            .filter(c -> c.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new CountryNotFoundException("Country not found"));
    }
```

**Update `CountryController.java`:**
```java
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }
```

**Test:** GET `http://localhost:8080/countries/az`
**Expected Response:** `404 Not Found` with body:
```json
{
  "timestamp": "...",
  "status": 404,
  "error": "Not Found",
  "message": "Country not found",
  "path": "/countries/az"
}
```

---

### Exercise 2.6-2.8: MockMVC Tests

**`src/test/java/com/cognizant/springlearn/SpringLearnApplicationTests.java`**
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
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound());
    }
}
```

---

### Exercise 4.1: POST - Add Country

**Task:** Create `/countries` POST endpoint to add a new country

**Update `CountryController.java`:**
```java
    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        LOGGER.info("Start");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End");
        return country;
    }
```

**Test:**
```bash
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"CA","name":"Canada"}' http://localhost:8080/countries
```

---

### Exercise 4.2: JSON to Bean Mapping

**Task:** Demonstrate automatic JSON to Java bean conversion

Spring automatically:
1. Parses JSON payload using Jackson
2. Creates `Country` object
3. Calls setters based on JSON keys (`code` → `setCode()`)
4. Passes object to controller method

**Test with typo:**
```bash
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"IN","nae":"India"}' http://localhost:8080/countries
```
**Response:** `{"code":"IN","name":null}`

---

### Exercise 4.3: Validate Country Code

**Task:** Validate that country code is exactly 2 characters

**Update `Country.java`:**
```java
package com.cognizant.springlearn.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Country {

    @NotNull
    @Size(min = 2, max = 2, message = "Country code should be 2 characters")
    private String code;

    private String name;

    // getters and setters
}
```

**Update `CountryController.java`:**
```java
    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End");
        return country;
    }
```

**Add dependency to `pom.xml`:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

### Exercise 4.4: Global Exception Handler

**Task:** Create centralized exception handling for all controllers

**`src/main/java/com/cognizant/springlearn/exception/GlobalExceptionHandler.java`**
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

**Test:**
```bash
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"I","name":"India"}' http://localhost:8080/countries
```

**Expected Response:** `400 Bad Request`
```json
{
  "timestamp": "...",
  "status": 400,
  "errors": ["Country code should be 2 characters"]
}
```

---

## Project 2: spring-rest-employee

### Exercise 3.1: Employee XML Configuration

**Task:** Create `employee.xml` with departments, skills, and 4 employees

**`src/main/resources/employee.xml`**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Departments -->
    <bean id="dept1" class="com.cognizant.springlearn.model.Department">
        <property name="id" value="1" />
        <property name="name" value="IT" />
    </bean>

    <bean id="dept2" class="com.cognizant.springlearn.model.Department">
        <property name="id" value="2" />
        <property name="name" value="HR" />
    </bean>

    <!-- Skills -->
    <bean id="skill1" class="com.cognizant.springlearn.model.Skill">
        <property name="id" value="1" />
        <property name="name" value="Java" />
    </bean>

    <bean id="skill2" class="com.cognizant.springlearn.model.Skill">
        <property name="id" value="2" />
        <property name="name" value="Spring" />
    </bean>

    <!-- Employees -->
    <bean id="emp1" class="com.cognizant.springlearn.model.Employee">
        <property name="id" value="1" />
        <property name="name" value="John" />
        <property name="salary" value="50000" />
        <property name="permanent" value="true" />
        <property name="dateOfBirth" value="15/05/1990" />
        <property name="department" ref="dept1" />
        <property name="skillList">
            <list>
                <ref bean="skill1" />
                <ref bean="skill2" />
            </list>
        </property>
    </bean>

    <!-- Add 3 more employees similarly -->

    <bean id="employeeList" class="java.util.ArrayList">
        <constructor-arg>
            <list>
                <ref bean="emp1" />
                <!-- ref other employees -->
            </list>
        </constructor-arg>
    </bean>

</beans>
```

---

### Exercise 3.2-3.4: Employee DAO, Service, Controller

**`src/main/java/com/cognizant/springlearn/dao/EmployeeDao.java`**
```java
package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = context.getBean("employeeList", List.class);
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public Employee getEmployee(int id) {
        return EMPLOYEE_LIST.stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void addEmployee(Employee employee) {
        EMPLOYEE_LIST.add(employee);
    }

    public void updateEmployee(Employee employee) {
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId() == employee.getId()) {
                EMPLOYEE_LIST.set(i, employee);
                return;
            }
        }
    }

    public void deleteEmployee(int id) {
        EMPLOYEE_LIST.removeIf(e -> e.getId() == id);
    }
}
```

**`src/main/java/com/cognizant/springlearn/service/EmployeeService.java`**
```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public Employee getEmployee(int id) {
        return employeeDao.getEmployee(id);
    }

    @Transactional
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }
}
```

**`src/main/java/com/cognizant/springlearn/controller/EmployeeController.java`**
```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("End");
        return employees;
    }
}
```

---

### Exercise 3.5: Department REST Service

**`src/main/java/com/cognizant/springlearn/controller/DepartmentController.java`**
```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        LOGGER.info("Start");
        List<Department> departments = departmentService.getAllDepartments();
        LOGGER.info("End");
        return departments;
    }
}
```

---

### Exercise 4.5: PUT - Update Employee with Validation

**Update `Employee.java` with validations:**
```java
package com.cognizant.springlearn.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Employee {

    @NotNull
    @Min(value = 1, message = "Id should be a number")
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;

    @NotNull
    @Min(value = 0, message = "Salary should be zero or above")
    private Double salary;

    @NotNull
    private Boolean permanent;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    private Department department;
    private List<Skill> skillList;

    // getters and setters
}
```

**Update `EmployeeController.java`:**
```java
    @PutMapping("/employees")
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.updateEmployee(employee);
        LOGGER.info("End");
    }
```

---

### Exercise 4.6: Handle Number Format Errors

**Update `GlobalExceptionHandler.java`:**
```java
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", "Bad Request");

        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
            for (InvalidFormatException.Reference reference : cause.getPath()) {
                body.put("message", "Incorrect format for field '" + reference.getFieldName() + "'");
            }
        }

        return new ResponseEntity<>(body, headers, status);
    }
```

---

### Exercise 4.7: DELETE - Delete Employee

**Update `EmployeeController.java`:**
```java
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.deleteEmployee(id);
        LOGGER.info("End");
    }
```

---

### Exercise 4.8: MockMVC Test - Employee Exception

**Add to test class:**
```java
    @Test
    void testUpdateEmployeeInvalidFormat() throws Exception {
        String invalidEmployee = "{"id":"abc","name":"John"}";

        ResultActions actions = mvc.perform(
            put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidEmployee)
        );

        actions.andExpect(status().isBadRequest());
    }
```

---

## Common `pom.xml` for Both Projects

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
        <version>2.7.14</version>
        <relativePath/>
    </parent>

    <groupId>com.cognizant</groupId>
    <artifactId>spring-rest</artifactId>
    <version>1.0</version>
    <name>spring-rest</name>
    <description>Spring REST Hands-On</description>

    <properties>
        <java.version>1.8</java.version>
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
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
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

---

## Testing Commands Summary

| Endpoint | Method | curl Command |
|----------|--------|-------------|
| `/hello` | GET | `curl -s http://localhost:8080/hello` |
| `/country` | GET | `curl -s http://localhost:8080/country` |
| `/countries` | GET | `curl -s http://localhost:8080/countries` |
| `/countries/{code}` | GET | `curl -s http://localhost:8080/countries/in` |
| `/countries` | POST | `curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"CA","name":"Canada"}' http://localhost:8080/countries` |
| `/employees` | GET | `curl -s http://localhost:8080/employees` |
| `/departments` | GET | `curl -s http://localhost:8080/departments` |
| `/employees` | PUT | `curl -i -H 'Content-Type: application/json' -X PUT -d '{"id":1,"name":"Updated"}' http://localhost:8080/employees` |
| `/employees/{id}` | DELETE | `curl -i -X DELETE http://localhost:8080/employees/1` |

---

## Run Tests

```bash
# All tests
mvn clean test

# Single test
mvn test -Dtest=SpringLearnApplicationTests#testGetCountry
```
