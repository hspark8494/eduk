package com.eduk.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.eduk.domain.ClassRoom;
import com.eduk.repository.ClassRoomRepository;
import com.eduk.service.ClassRoomService;
//import com.eduk.service.ClassRoomServiceImpl;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/classRoom")
@RequiredArgsConstructor
public class ClassRoomController {

	/*
	 * @Autowired private ClassRoomService classRoomService; //private
	 * ClassRoomServiceImpl classRoomServiceImpl;
	 * 
	 * //강의 생성
	 * 
	 * @GetMapping("/regClassRoom") public String regClassRoom(@RequestParam String
	 * classRoomName,
	 * 
	 * @RequestParam String detail, @RequestParam LocalDateTime createTime,
	 * 
	 * @RequestParam LocalDateTime updateTime, @RequestParam int state,
	 * 
	 * @RequestParam String classRoomImage) { return ""; }
	 * 
	 * //강의 설정
	 * 
	 * @PutMapping("/classRoom/{classRoomId}") public ClassRoom
	 * updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long
	 * classRoomId) {
	 * 
	 * return classRoomRepository.findById(classRoomId).map(classRoom -> {
	 * classRoom.; }
	 * 
	 * //강의 삭제
	 * 
	 * @DeleteMapping("/classRoom/{classRoomId}") public void
	 * deleteClassRoom(@PathVariable Long classRoomId) {
	 * //ClassRoomRepository.deleteById(classRoomId); return ; }
	 */
}