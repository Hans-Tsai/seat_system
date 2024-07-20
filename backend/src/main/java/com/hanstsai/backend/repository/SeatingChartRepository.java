package com.hanstsai.backend.repository;

import com.hanstsai.backend.model.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Long> {
}
