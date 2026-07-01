package com.cts.employee.service;

import com.cts.employee.dto.EmployeeDTO;
import com.cts.employee.dto.PaginatedResponse;
import com.cts.employee.entity.Employee;
import com.cts.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "salary", "joinDate", "id");
    private static final String DEFAULT_SORT_FIELD = "id";

    public PaginatedResponse<EmployeeDTO> findEmployees(int page, int size, String sortBy, String direction) {
        String sortField = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : DEFAULT_SORT_FIELD;

        Sort sort;
        try {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(Sort.Direction.DESC, sortField)
                    : Sort.by(Sort.Direction.ASC, sortField);
        } catch (IllegalArgumentException e) {
            sort = Sort.by(Sort.Direction.ASC, sortField);
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeDTO> data = employeePage.getContent()
                .stream()
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());

        return PaginatedResponse.<EmployeeDTO>builder()
                .data(data)
                .currentPage(employeePage.getNumber())
                .totalPages(employeePage.getTotalPages())
                .totalElements(employeePage.getTotalElements())
                .pageSize(employeePage.getSize())
                .hasNext(employeePage.hasNext())
                .hasPrevious(employeePage.hasPrevious())
                .build();
    }
}
