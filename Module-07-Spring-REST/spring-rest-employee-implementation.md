# Project 2: spring-rest-employee Implementation

## Overview

Employee REST API demonstrating Full CRUD operations, Department integration, Validation, and Exception Handling.

**Covers Exercises:** 3.1 - 3.5, 4.5 - 4.8

**Package:** `com.cognizant.springlearn`

---

## Project Structure

```
spring-rest-employee/
├── pom.xml
├── src/main/java/com/cognizant/springlearn/
│   ├── SpringLearnApplication.java
│   ├── model/
│   │   ├── Employee.java
│   │   ├── Department.java
│   │   └── Skill.java
│   ├── dao/
│   │   └── EmployeeDao.java
│   ├── service/
│   │   ├── EmployeeService.java
│   │   └── DepartmentService.java
│   ├── controller/
│   │   ├── EmployeeController.java
│   │   └── DepartmentController.java
│   └── exception/
│       ├── EmployeeNotFoundException.java
│       └── GlobalExceptionHandler.java
├── src/main/resources/
│   ├── application.properties
│   └── employee.xml
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
    <artifactId>spring-rest-employee</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-rest-employee</name>
    <description>Employee REST API - Exercises 3.1-3.5, 4.5-4.8</description>

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

## Step 2: Models

### Department.java

**Exercise 3.1**

```java
package com.cognizant.springlearn.model;

public class Department {

    private int id;
    private String name;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}
```

### Skill.java

**Exercise 3.1**

```java
package com.cognizant.springlearn.model;

public class Skill {

    private int id;
    private String name;

    public Skill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill [id=" + id + ", name=" + name + "]";
    }
}
```

### Employee.java

**Exercise 3.1** (basic POJO) + **Exercise 4.5** (validation)

```java
package com.cognizant.springlearn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
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

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary
                + ", permanent=" + permanent + ", dateOfBirth=" + dateOfBirth
                + ", department=" + department + ", skillList=" + skillList + "]";
    }
}
```

---

## Step 3: Spring XML Configuration - employee.xml

**Exercise 3.1**

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

    <bean id="skill3" class="com.cognizant.springlearn.model.Skill">
        <property name="id" value="3" />
        <property name="name" value="Hibernate" />
    </bean>

    <!-- Employee 1 -->
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

    <!-- Employee 2 -->
    <bean id="emp2" class="com.cognizant.springlearn.model.Employee">
        <property name="id" value="2" />
        <property name="name" value="Jane" />
        <property name="salary" value="60000" />
        <property name="permanent" value="true" />
        <property name="dateOfBirth" value="20/08/1988" />
        <property name="department" ref="dept2" />
        <property name="skillList">
            <list>
                <ref bean="skill2" />
                <ref bean="skill3" />
            </list>
        </property>
    </bean>

    <!-- Employee 3 -->
    <bean id="emp3" class="com.cognizant.springlearn.model.Employee">
        <property name="id" value="3" />
        <property name="name" value="Bob" />
        <property name="salary" value="45000" />
        <property name="permanent" value="false" />
        <property name="dateOfBirth" value="10/03/1995" />
        <property name="department" ref="dept1" />
        <property name="skillList">
            <list>
                <ref bean="skill1" />
            </list>
        </property>
    </bean>

    <!-- Employee 4 -->
    <bean id="emp4" class="com.cognizant.springlearn.model.Employee">
        <property name="id" value="4" />
        <property name="name" value="Alice" />
        <property name="salary" value="70000" />
        <property name="permanent" value="true" />
        <property name="dateOfBirth" value="25/12/1985" />
        <property name="department" ref="dept2" />
        <property name="skillList">
            <list>
                <ref bean="skill1" />
                <ref bean="skill2" />
                <ref bean="skill3" />
            </list>
        </property>
    </bean>

    <bean id="employeeList" class="java.util.ArrayList">
        <constructor-arg>
            <list>
                <ref bean="emp1" />
                <ref bean="emp2" />
                <ref bean="emp3" />
                <ref bean="emp4" />
            </list>
        </constructor-arg>
    </bean>

    <bean id="departmentList" class="java.util.ArrayList">
        <constructor-arg>
            <list>
                <ref bean="dept1" />
                <ref bean="dept2" />
            </list>
        </constructor-arg>
    </bean>

</beans>
```

---

## Step 4: DAO - EmployeeDao.java

**Exercise 3.2**

```java
package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);
    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = context.getBean("employeeList", List.class);
        LOGGER.info("END");
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return EMPLOYEE_LIST;
    }

    public Employee getEmployee(int id) {
        LOGGER.info("START");
        Employee employee = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        LOGGER.info("END");
        return employee;
    }

    public void addEmployee(Employee employee) {
        LOGGER.info("START");
        EMPLOYEE_LIST.add(employee);
        LOGGER.info("END");
    }

    public void updateEmployee(Employee employee) {
        LOGGER.info("START");
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId() == employee.getId()) {
                EMPLOYEE_LIST.set(i, employee);
                break;
            }
        }
        LOGGER.info("END");
    }

    public void deleteEmployee(int id) {
        LOGGER.info("START");
        EMPLOYEE_LIST.removeIf(e -> e.getId() == id);
        LOGGER.info("END");
    }
}
```

---

## Step 5: Services

### EmployeeService.java

**Exercises 3.2 - 3.4 + 4.5 + 4.7**

```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public Employee getEmployee(int id) {
        LOGGER.info("START");
        Employee employee = employeeDao.getEmployee(id);
        LOGGER.info("END");
        return employee;
    }

    @Transactional
    public void addEmployee(Employee employee) {
        LOGGER.info("START");
        employeeDao.addEmployee(employee);
        LOGGER.info("END");
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        LOGGER.info("START");
        employeeDao.updateEmployee(employee);
        LOGGER.info("END");
    }

    @Transactional
    public void deleteEmployee(int id) {
        LOGGER.info("START");
        employeeDao.deleteEmployee(id);
        LOGGER.info("END");
    }
}
```

### DepartmentService.java

**Exercise 3.5**

```java
package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    public List<Department> getAllDepartments() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        List<Department> departments = context.getBean("departmentList", List.class);
        LOGGER.info("END");
        return departments;
    }
}
```

---

## Step 6: Controllers

### EmployeeController.java

**Exercises 3.3 - 3.4 + 4.5 + 4.7**

```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import jakarta.validation.Valid;
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

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        LOGGER.info("End");
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        LOGGER.info("Start");
        LOGGER.debug("Employee: {}", employee);
        employeeService.addEmployee(employee);
        LOGGER.info("End");
        return employee;
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.updateEmployee(employee);
        LOGGER.info("End");
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.deleteEmployee(id);
        LOGGER.info("End");
    }
}
```

### DepartmentController.java

**Exercise 3.5**

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

## Step 7: Exception Handling

### EmployeeNotFoundException.java

**Exercise 3.4**

```java
package com.cognizant.springlearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
```

### GlobalExceptionHandler.java

**Exercises 4.4 + 4.6**

```java
package com.cognizant.springlearn.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            org.springframework.http.converter.HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOGGER.info("Start");

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

**Exercises 4.8**

```java
package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(employeeController);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        ResultActions actions = mvc.perform(get("/employees"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testGetEmployee() throws Exception {
        ResultActions actions = mvc.perform(get("/employees/1"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.name").value("John"));
        actions.andExpect(jsonPath("$.department.name").value("IT"));
    }

    @Test
    void testGetEmployeeNotFound() throws Exception {
        ResultActions actions = mvc.perform(get("/employees/999"));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void testAddEmployee() throws Exception {
        String employee = "{\"id\":5,\"name\":\"Test\",\"salary\":40000,"
                + "\"permanent\":true,\"dateOfBirth\":\"01/01/2000\"}";

        ResultActions actions = mvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee)
        );
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void testUpdateEmployeeInvalidFormat() throws Exception {
        String invalidEmployee = "{\"id\":\"abc\",\"name\":\"John\"}";

        ResultActions actions = mvc.perform(
                put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployee)
        );

        actions.andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        ResultActions actions = mvc.perform(delete("/employees/1"));
        actions.andExpect(status().isOk());
    }

    @Test
    void testGetDepartments() throws Exception {
        ResultActions actions = mvc.perform(get("/departments"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].name").value("IT"));
    }
}
```

---

## Testing Commands

### GET Endpoints

```bash
# Get all employees
curl -s http://localhost:8080/employees

# Get employee by ID
curl -s http://localhost:8080/employees/1

# Get employee not found (returns 404)
curl -s http://localhost:8080/employees/999

# Get all departments
curl -s http://localhost:8080/departments
```

### POST Endpoint

```bash
# Add a new employee
curl -i -H 'Content-Type: application/json' -X POST \
  -d '{"id":5,"name":"Test","salary":40000,"permanent":true,"dateOfBirth":"01/01/2000"}' \
  http://localhost:8080/employees

# Validation error - name too long
curl -i -H 'Content-Type: application/json' -X POST \
  -d '{"id":6,"name":"ThisNameIsWayTooLongForValidation","salary":40000,"permanent":true,"dateOfBirth":"01/01/2000"}' \
  http://localhost:8080/employees
```

### PUT Endpoint

```bash
# Update an employee
curl -i -H 'Content-Type: application/json' -X PUT \
  -d '{"id":1,"name":"Updated John","salary":55000,"permanent":true,"dateOfBirth":"15/05/1990"}' \
  http://localhost:8080/employees

# Invalid format error
curl -i -H 'Content-Type: application/json' -X PUT \
  -d '{"id":"abc","name":"John"}' \
  http://localhost:8080/employees
```

### DELETE Endpoint

```bash
# Delete an employee
curl -i -X DELETE http://localhost:8080/employees/1
```

### Run Tests

```bash
# Run all tests
mvn clean test

# Run specific test
mvn test -Dtest=SpringLearnApplicationTests#testGetAllEmployees
```

---

## API Endpoints Summary

| Endpoint | Method | Description | Request Body |
|----------|--------|-------------|--------------|
| `/employees` | GET | Get all employees | - |
| `/employees/{id}` | GET | Get employee by ID | - |
| `/employees` | POST | Add new employee | Employee JSON |
| `/employees` | PUT | Update employee | Employee JSON |
| `/employees/{id}` | DELETE | Delete employee | - |
| `/departments` | GET | Get all departments | - |

---

## Run Application

```bash
cd Module-07-Spring-REST\spring-rest-employee
mvn spring-boot:run
```
