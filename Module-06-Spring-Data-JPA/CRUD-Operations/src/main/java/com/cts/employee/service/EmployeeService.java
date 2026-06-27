package com.cts.employee.service;

import com.cts.employee.dto.EmployeeDTO;
import com.cts.employee.entity.Department;
import com.cts.employee.entity.Employee;
import com.cts.employee.exception.DuplicateEmailException;
import com.cts.employee.exception.ResourceNotFoundException;
import com.cts.employee.repository.EmployeeRepository;
import com.cts.employee.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }

        Employee employee = mapToEntity(dto);

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
            employee.setDepartment(department);
        }

        Employee saved = employeeRepository.save(employee);
        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail())) {
            if (employeeRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateEmailException(dto.getEmail());
            }
            existing.setEmail(dto.getEmail());
        }
        if (dto.getSalary() != null) {
            existing.setSalary(dto.getSalary());
        }
        if (dto.getJoinDate() != null) {
            existing.setJoinDate(dto.getJoinDate());
        }
        if (dto.getActive() != null) {
            existing.setActive(dto.getActive());
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
            existing.setDepartment(department);
        }

        Employee updated = employeeRepository.save(existing);
        return mapToDTO(updated);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setActive(false);
        employeeRepository.save(employee);
    }

    public void hardDeleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO dto = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .joinDate(employee.getJoinDate())
                .active(employee.getActive())
                .build();

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        return dto;
    }

    private Employee mapToEntity(EmployeeDTO dto) {
        Employee employee = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .salary(dto.getSalary())
                .joinDate(dto.getJoinDate())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();

        return employee;
    }
}
