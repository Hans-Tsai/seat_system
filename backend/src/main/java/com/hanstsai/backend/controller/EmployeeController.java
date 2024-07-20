package com.hanstsai.backend.controller;

import com.hanstsai.backend.model.Employee;
import com.hanstsai.backend.service.EmployeeService;
import com.hanstsai.backend.service.SeatingChartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SeatingChartService seatingChartService;

    @GetMapping("/all_employees")
    public List<Employee> getAllSeatingCharts() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/change_seat")
    @Transactional
    public Boolean changeSeat(@RequestBody Map<String, Integer> payload) {
        try {
            int current_emp_id = payload.get("emp_id");

            Long current_emp_id_long = Long.valueOf(current_emp_id);
            Optional<Employee> emp = employeeService.getEmployee(current_emp_id_long);
            if (emp.isEmpty()) throw new Exception("Employee not found");
            int origin_floor_seat_seq = emp.get().getFloor_seat_seq();

            int new_floor_seat_seq = payload.get("new_floor_seat_seq");

            employeeService.updateEmployee(current_emp_id, new_floor_seat_seq);
            seatingChartService.updateOriginSeatingChart(origin_floor_seat_seq);
            seatingChartService.updateNewSeatingChart(new_floor_seat_seq);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
