package com.cts.employee.controller;

import com.cts.employee.dto.EmployeeDTO;
import com.cts.employee.dto.PaginatedResponse;
import com.cts.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<EmployeeDTO>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        PaginatedResponse<EmployeeDTO> response = employeeService.findEmployees(page, size, sortBy, direction);
        return ResponseEntity.ok(response);
    }
}
