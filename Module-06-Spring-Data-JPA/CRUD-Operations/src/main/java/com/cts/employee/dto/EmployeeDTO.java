package com.cts.employee.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be non-negative")
    private Double salary;

    private Long departmentId;

    private String departmentName;

    @Past(message = "Join date must be in the past")
    private LocalDate joinDate;

    private Boolean active;
}
