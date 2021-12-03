package com.eduk.controller;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.domain.Participant;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.ClassRoomService;
//import com.eduk.service.ClassRoomServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClassRoomController {
	
	@Autowired
	private ClassRoomService classRoomService;
	//private ClassRoomServiceImpl classRoomServiceImpl;
	
	//강의 생성
	@PostMapping("/class-room")
	public ResponseEntity<Map<String, Object>> createClassRoom(@RequestBody ClassRoom classRoom, @RequestHeader HttpHeaders headers){
		System.out.println(headers);
		Long id = TokenProvider.getIdFormHeader(headers);
		System.out.println(id);
		ClassRoom newClassRoom = classRoomService.createWithJoin(classRoom, new Member(id));
		
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("newClassRoom", newClassRoom);
		
		return new ResponseEntity<Map<String,Object>>(respMap, HttpStatus.OK);
	}
	
	@GetMapping("/class-room-list")
	public List<ClassRoom> getClassRoomList(@RequestHeader HttpHeaders headers){
		System.out.println(headers);
		//System.out.println(headers);
		//Long id = TokenProvider.getIdFormHeader(headers);
		return classRoomService.findAllByMemberId(1L);
	}
	
	@GetMapping("/regClassRoom")
	public String regClassRoom(@RequestParam String classRoomName, 
			@RequestParam String detail, @RequestParam LocalDateTime createTime, 
			@RequestParam LocalDateTime updateTime, @RequestParam int state, 
			@RequestParam String classRoomImage) {
		return "";
	}
	/*
	//강의 설정
	@PutMapping("/classRoom/{classRoomId}")
	public ClassRoom updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long classRoomId) {
	
		return classRoomRepository.findById(classRoomId).map(classRoom ->
		{
			classRoom.;
	}*/
	
	//강의 삭제
	@DeleteMapping("/classRoom/{classRoomId}")
	public void deleteClassRoom(@PathVariable Long classRoomId) {
		//ClassRoomRepository.deleteById(classRoomId);
		return ;
	}
}