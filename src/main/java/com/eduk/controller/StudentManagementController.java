package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Member;
import com.eduk.service.StudentManagementService;

/**
 * 강사가 수강생 목록 조회 및 학생정보조회, 출결사항, 회원추방 
 * */
@RestController
@RequestMapping("/{classRoomId}")
public class StudentManagementController {
	@Autowired
	private StudentManagementService studentManagementService;
	
	/**
	 * 강사가 수강생 목록 조회 
	 * */
	@GetMapping("/admin/member/all")
	public List<Member> selectMember(
			@RequestHeader HttpHeaders headers
			) {
		List<Member>list = studentManagementService.selectMember();
		return list;
	}
	
	/**
	 * 학생정보 조회 
	 * */
	@GetMapping("/admin/member/{memberId}")
	public Member selectByMemberId(@PathVariable Long memberId) {
		Member member = studentManagementService.selectByMemberId(memberId);
		return member;
	}
	
	/**
	 * 학생정보 수정 
	 * */
	@PutMapping("/admin/member/{memberId}")
	public Member updateMember(@PathVariable Long memberId, @RequestBody Member member) {
		Member memberEntity = studentManagementService.updateMember(member);
		return memberEntity;
	}

	
	/**
	 * 특정학생 탈퇴 
	 * */
	@DeleteMapping("/admin/member/{memberId}")
	public void memberDelete(@PathVariable Long memberId) {
		studentManagementService.deleteMember(memberId);
	}
	
}



