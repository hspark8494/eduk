package com.eduk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{

}
