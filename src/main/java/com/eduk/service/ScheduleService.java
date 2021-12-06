package com.eduk.service;

import com.eduk.domain.Schedule;

public interface ScheduleService {
	/**
	 * 시간표 생성 
	 * */
	Schedule createSchedule(Schedule schedule);
	
	/**
	 * 시간표 수정
	 * */
	Schedule updateSchedule(Schedule schedule);
	
	/**
	 * 시간표 삭제 
	 * */
	void deleteSchedule(Long scheduleId);
}
