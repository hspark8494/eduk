package com.eduk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.Schedule;
import com.eduk.service.ScheduleServiceImpl;

/**
 * 스케줄 생성 및 수정,삭제 
 * */
@RestController
@RequestMapping("/{channelId}")
public class ScheduleController {
	
	@Autowired
	private ScheduleServiceImpl scheduleServiceImpl;
	
	/**
	 * 스케줄 생성 
	 * */
	@PostMapping("/schedule")
	public void createSchedule(@RequestBody Schedule schedule, @PathVariable Long channelId) {
		schedule.setChannel(Channel.builder().channelId(channelId).build());
		scheduleServiceImpl.createSchedule(schedule);
	}
	
	/**
	 * 스케줄 수정
	 * */
	@PutMapping("/schedule/{scheduleId}")
	public Schedule updateSchedule(@RequestBody Schedule schedule, @PathVariable Long scheduleId) {
		Schedule scheduleEntity =scheduleServiceImpl.updateSchedule(schedule);
		return scheduleEntity;
	}
	
	/**
	 * 스케줄 삭제 
	 * */
	@DeleteMapping("/schedule/{scheduleId}")
	public void deleteSchedule(@PathVariable Long scheduleId) {
		scheduleServiceImpl.deleteSchedule(scheduleId);
	}
}









