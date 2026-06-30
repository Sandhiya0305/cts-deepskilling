package com.cts.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.cts.employee.entity.Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private Double salary;

    @Column(length = 50)
    private String department;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', email='" + email + "', salary=" + salary + "}";
    }
}
