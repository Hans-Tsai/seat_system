package com.hanstsai.backend.service;

import com.hanstsai.backend.model.SeatingChart;
import com.hanstsai.backend.repository.SeatingChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingChartService {

    private final SeatingChartRepository seatingChartRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SeatingChartService(SeatingChartRepository seatingChartRepository, JdbcTemplate jdbcTemplate) {
        this.seatingChartRepository = seatingChartRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SeatingChart> getAllSeatingCharts() {
        return seatingChartRepository.findAll();
    }

    public void updateOriginSeatingChart(int origin_floor_seat_seq) {
        jdbcTemplate.update("CALL UpdateOriginSeatingChart(?)", origin_floor_seat_seq);
    }

    public void updateNewSeatingChart(int new_floor_seat_seq) {
        jdbcTemplate.update("CALL UpdateNewSeatingChart(?)", new_floor_seat_seq);
    }
}
