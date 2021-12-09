package com.eduk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.repository.MemberRepository;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.ClassRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClassRoomController {
	
	private final ClassRoomService classRoomService;
	private final MemberRepository memberRepo;
	
	//수강생 정보 출력
	@GetMapping("/class-participant")
	public ArrayList<Member> readParticipant(
			@RequestParam("memberId") Long memberId, @RequestParam("profileImage") String profileImage, @RequestParam("state") int state)
			{
				return memberRepo.findByMemberId(memberId);
			}
	
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
	
	@GetMapping("/class-room/list")
	public List<ClassRoom> getClassRoomList(@RequestHeader HttpHeaders headers){
		System.out.println(headers);
		//System.out.println(headers);
		Long id = TokenProvider.getIdFormHeader(headers);
		System.out.println(id);
		List<ClassRoom> classRoom = classRoomService.findAllByMemberId(id);
		return classRoom;
	}
	
	/**
	 * 강의 정보 가져오기
	 * @param classRoomId
	 * @param headers
	 * @return
	 */
	@GetMapping("/class-room/{classRoomId}")
	public Map<String,Object> getClassRoomInfo(@PathVariable Long classRoomId, @RequestHeader HttpHeaders headers) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("classRoom", classRoomService.getClassRoom(classRoomId));
		respMap.put("participants", classRoomService.getParticipants(classRoomId));
		return respMap;
	}
	
	//강의 설정(수정)
	@PutMapping("/updateclassRoom/{classRoomId}")
	public ClassRoom updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long classRoomId) {
		ClassRoom classRoomEntity = classRoomService.updateClassRoom(classRoom);
		return classRoomEntity;
	}
	
	//강의 삭제
	@DeleteMapping("/deleteClassRoom/{classRoomId}")
	public void deleteClassRoom(@PathVariable Long classRoomId) {
		classRoomService.deleteClassRoom(classRoomId);
	}
}