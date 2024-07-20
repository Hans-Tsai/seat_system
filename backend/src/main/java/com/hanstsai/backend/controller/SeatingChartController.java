package com.hanstsai.backend.controller;

import com.hanstsai.backend.model.SeatingChart;
import com.hanstsai.backend.service.SeatingChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seatingchart")
@CrossOrigin(origins = "http://localhost:8081")
public class SeatingChartController {

    @Autowired
    private SeatingChartService seatingChartService;

    @GetMapping("/all_seating")
    public List<SeatingChart> getAllSeatingCharts() {
        return seatingChartService.getAllSeatingCharts();
    }
}
