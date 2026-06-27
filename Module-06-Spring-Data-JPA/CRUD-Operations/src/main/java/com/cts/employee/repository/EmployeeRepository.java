package com.cts.employee.repository;

import com.cts.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    List<Employee> findByDepartmentName(String deptName);

    List<Employee> findByJoinDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT e FROM Employee e WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2)")
    List<Employee> findEmployeesAboveAverageSalary();

    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName AND e.salary >= :minSalary")
    List<Employee> findByDepartmentAndMinSalary(@Param("deptName") String deptName, @Param("minSalary") Double minSalary);

    boolean existsByEmail(String email);
}
