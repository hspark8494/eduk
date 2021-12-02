package com.eduk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduk.domain.ClassRoom;
import com.eduk.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;
/*
@Service
@RequiredArgsConstructor
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService{

	
	//강의 생성하기
	@Override
	public void regClassRoom(ClassRoom classRoom) {
		ClassRoom classRoom = ClassRoomRepository.save(classRoom);
		System.out.println(classRoom.getClassRoomName());
		
	}
	//강의 설정하기
	@Override
	public ClassRoom updateClassRoom(ClassRoom classRoom) {
		ClassRoom classRoom = classRoomRepository.findbyId(classRoom.getClassRoomId()).orElse(null);
		if(classRoom==null) throw new RuntimeException("강의실번호 오류로 삭제할 수 없습니다.");
		
		classRoom.setClassRoomName(classRoom.getClassRoomName().replace);
		classRoom.setDetail(classRoom.getDetail());
		classRoom.setState(classRoom.getState());
		classRoom.setClassRoomImage(classRoom.getClassRoomImage());
		return classRoom;
	}
	//강의 삭제하기
	@Override
	public void deleteClassRoom(Long classRoomId) {
		ClassRoom classRoom = ClassRoomRepository.findById(classRoomId).orElse(null);
		if(classRoom==null) throw new RuntimeException("강의실번호 오류로 삭제할 수 없습니다.");
		
		ClassRoomRepository.delete(classRoom);
	}
	
	
}*/
