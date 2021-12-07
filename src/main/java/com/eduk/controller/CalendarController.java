package com.eduk.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Calendar;
import com.eduk.repository.CalendarRepository;

@RestController
@Transactional
public class CalendarController {
	@Autowired CalendarRepository calc;
	
	@PostMapping("/get-calendar")
	public Calendar getCalendar() {
		
		return calc.getById(1L);
	}
	
	@PostMapping("/calendar")
	public String setCalendar(@RequestBody String data) {
		System.out.println(data);
		Calendar cac = calc.getById(1L);
		cac.setCalendarContent(data);
		return cac.getCalendarContent();
	}
}
