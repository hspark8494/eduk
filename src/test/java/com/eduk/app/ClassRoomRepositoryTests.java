package com.eduk.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.eduk.domain.ClassRoom;
import com.eduk.repository.ClassRoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class ClassRoomRepositoryTests {

	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	/*
	//강의 생성 테스트	
	@Test
	public void testInsert() {
		ClassRoom classRoom = new ClassRoom();
		classRoom.setclassRoomName("강의명");
		classRoom.setDetail("강의내용");
		//생성일, 수정일
		classRoom.setstate("상태");
		classRoom.setclassRoomImage("");
		
		classRoomRepository.save(classRoom);
	}
	//강의 조회 테스트
	@Test
	public void testSearch() {
		classRoomRepository.findById(1L).ifPresent((classRoom)->{
				System.out.println(classRoom);});
	}
	//강의 수정 테스트
	@Test
	public void testUpdate() {
		System.out.println("Search First---");
		ClassRoom classRoom = classRoomRepository.findOne(1L);
		System.out.println("Update----");
		classRoom.setclassRoomName("강의명이 수정되었습니다.");
		System.out.println("Save----");
		classRoomRepository.save(classRoom);
	}
	//강의 삭제 테스트
	@Test
	public void testDelete(){
		System.out.println("DELETE Entity ");
		classRoomRepository.delete(1L);
	}*/
}
