package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
