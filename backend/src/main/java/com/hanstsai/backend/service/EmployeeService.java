package com.hanstsai.backend.service;

import com.hanstsai.backend.model.Employee;
import com.hanstsai.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long current_emp_id_long) {
        return employeeRepository.findById(current_emp_id_long);
    }

    public void updateEmployee(int current_emp_id, int new_floor_seat_seq) {
        jdbcTemplate.update("CALL UpdateEmployee(?, ?)", current_emp_id, new_floor_seat_seq);
    }
}
