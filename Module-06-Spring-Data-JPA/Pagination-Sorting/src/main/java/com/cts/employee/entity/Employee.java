package com.cts.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    private Double salary;

    @Column(length = 50)
    private String department;

    private LocalDate joinDate;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;
}
