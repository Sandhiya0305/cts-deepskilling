# Task Description

Create a Data Transfer Object for Employee with validation annotations. Fields: id (Long, ignored on create), name (@NotBlank, @Size(min=2, max=100)), email (@NotBlank, @Email), salary (@NotNull, @Min(0), @Max(1000000)), departmentId (@NotNull), dateOfBirth (@Past, @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)). Create a mapper class EmployeeMapper with toDTO(Employee) and toEntity(EmployeeDTO) methods. Explain why DTOs are used instead of exposing entities directly: decoupling, security (hide sensitive fields), validation separation, API versioning.
