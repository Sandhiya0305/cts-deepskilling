package com.cts.employee.dto;

import com.cts.employee.entity.Employee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private Double salary;
    private String department;
    private LocalDate joinDate;
    private Boolean active;

    public static EmployeeDTO fromEntity(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .joinDate(employee.getJoinDate())
                .active(employee.getActive())
                .build();
    }
}
