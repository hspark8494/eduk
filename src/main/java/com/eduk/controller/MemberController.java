package com.eduk.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.service.EmailService;
import com.eduk.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	EmailService emailService;

	/**
	 * 회원 이메일 입력 후 인증 이메일 발송
	 */
	@ResponseBody
	// @RequestMapping(value = "/mail", method = RequestMethod.GET)
	@PostMapping("/mail")
	public void emailConfirm(@RequestBody Member member) throws Exception {
		System.out.println("전달 받은 이메일 : " + member.getEmail());
		emailService.sendSimpleMessage(member.getEmail());
	}

	/**
	 * 인증코드 받고 맞는지 확인 인증코드 일치 - 1, 불일치 - 0
	 */
	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(@PathVariable String code) {
		System.out.println("Post verifyCode");

		int result = 0;
		System.out.println("code : " + code);
		System.out.println("code match : " + emailService.ePw.equals(code));
		if (emailService.ePw.equals(code)) {
			result = 1;
		}

		return result;
	}

	/**
	 * 회원 가입, 유효성 확인
	 *//*
		 * @PostMapping("/register") // @RequestMapping(value = "/register", method =
		 * RequestMethod.GET) public String register(@Valid Member member, Errors
		 * errors, Model model) { // model 객체 - 유효성 검사에서 통과 못한 필드 에러값 저장
		 * System.out.printf("%s %s %s\n", member.getEmail(), member.getName(),
		 * member.getPassword());
		 * 
		 * if (errors.hasErrors()) { // 회원가입 실패 시, 입력 데이터를 유지
		 * model.addAttribute("member", member); // 유효성 통과 못한 필드와 메세지 model에 저장
		 * Map<String, String> validatorResult = memberService.register(errors); // 유효성
		 * 체크 for (String key : validatorResult.keySet()) { // 실패한 필드명이 들어있는 key값만 꺼내
		 * String key로 저장. model.addAttribute(key, validatorResult.get(key)); // 키에 해당하는
		 * 값을 꺼낸 뒤, key와 value 모두 저장 } // 입력값 형식이 맞지 않을 때 // return ""; }
		 * 
		 * // 아이디 중복체크 if (idCheck(member.getEmail())) { // 중복이 있을 때 // return ""; }
		 * 
		 * // 회원 추가 // memberService.saveMember(member); return ""; }
		 */

	/**
	 * 아이디(이메일) 중복 체크
	 */
	// @RequestMapping(value = "/check", method = RequestMethod.GET) // 테스트용
	@GetMapping("/idCheck")
	public boolean idCheck(@PathVariable String email) {

		try {
			if (memberService.findByEmail(email).isPresent()) { // 값이 있을때 true 반환

				return true; // 값이 있을 때
			}
		} catch (NoSuchElementException e) {
			System.out.println("일치하는 값이 없음");
		}
		return false;
	}

	/**
	 * 이메일 유효성 검사
	 */
	// @RequestMapping(value = "/emailCheck", method = RequestMethod.GET) //테스트용
	public boolean isEmail(@PathVariable String email) {
		if (memberService.isEmail(email)) {
			return true; // 이메일 형식 맞을때
		}
		return false; // 이메일 형식 아닐때

	}

	/**
	 * 비밀번호 유효성 검사 특수문자, 영문, 숫자 조합 (8~10 자리)
	 */
	// @RequestMapping(value = "/pwdCheck", method = RequestMethod.GET) //테스트용
	public boolean isPassword(@PathVariable String pwd) {

		if (memberService.isPassword(pwd)) {
			return true; // 비밀번호 형식 맞을때
		}
		return false; // 비밀번호 형식 아닐때
	}

	/**
	 * 회원 탈퇴
	 */
	@DeleteMapping("/deleteMember")
	public void deleteMember(@RequestBody Member member) {
		member.setState(0);
		memberService.deleteMember(member);
	}

	/**
	 * 회원정보 조회
	 */
	// @RequestMapping(value = "/selectMemberInfo", method = RequestMethod.GET)
	// //테스트용
	@GetMapping("/selectMemberInfo")
	public Optional<Member> selectMember(@PathVariable Long memberId) {

		Optional<Member> member = memberService.selectMemberInfo(memberId);

		try {

			System.out.println(member.get().toString());
			return member;

		} catch (NoSuchElementException e) {
			System.out.println("정보 없음");
		}
		return null;
	}

	/**
	 * 회원정보 수정 + 프로필 이미지
	 */
	@PutMapping("/updateMemberInfo")
	public void updateMember(@RequestBody Member member) {

		memberService.updateMemberInfo(member);

	}

	/**
	 * 회원의 강의목록 조회
	 */
	@GetMapping("/selectClass")
	public List<ClassRoom> selectMemberClass(@PathVariable Long memberId) {

		List<ClassRoom> classRoom = memberService.selectMemberClassRoom(memberId);

		try {

			if (!classRoom.isEmpty()) { // 값이 있을때 true
				System.out.println(classRoom.toString());
				return classRoom;
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("정보 없음");
		}

		return null;
	}

}
