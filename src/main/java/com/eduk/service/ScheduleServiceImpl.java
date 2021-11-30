package com.eduk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Schedule;
import com.eduk.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public void createSchedule(Schedule schedule) {
		scheduleRepository.save(schedule);
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) {
		Schedule scheduleEntity = scheduleRepository.findById(schedule.getScheduleId()).orElse(null);
		if(scheduleEntity==null) throw new RuntimeException("수정할 수 없습니다.");
		
		scheduleEntity.setTitle(schedule.getTitle());
		scheduleEntity.setScheduleDate(schedule.getScheduleDate());
		scheduleEntity.setColor(schedule.getColor());
		
		return scheduleEntity;
	}

	@Override
	public void deleteSchedule(Long scheduleId) {
		Schedule scheduleEntity = scheduleRepository.findById(scheduleId).orElse(null);
		if(scheduleEntity==null) throw new RuntimeException("삭제할 수 없습니다.");
		
		scheduleRepository.deleteById(scheduleId);
	}



}
