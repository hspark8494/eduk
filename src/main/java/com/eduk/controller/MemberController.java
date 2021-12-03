package com.eduk.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.eduk.domain.Member;
import com.eduk.service.EmailService;
import com.eduk.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(maxAge = 3600)
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	EmailService emailService;

	//@PostMapping("/mail")
	@ResponseBody
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public void emailConfirm(Member member) throws Exception {
		System.out.println("post emailConfirm");
		System.out.println("전달 받은 이메일 : " + member.getEmail());
		emailService.sendSimpleMessage(member.getEmail());
	}

	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(String code) {
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
	 */
	@PostMapping("/register")
	// @RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@Valid Member member, Errors errors, Model model) { // model 객체 - 유효성 검사에서 통과 못한 필드 에러값 저장
		System.out.printf("%s %s %s\n", member.getEmail(), member.getName(), member.getPassword());

		if (errors.hasErrors()) {
			// 회원가입 실패 시, 입력 데이터를 유지
			model.addAttribute("member", member);
			// 유효성 통과 못한 필드와 메세지 model에 저장
			Map<String, String> validatorResult = memberService.register(errors); // 유효성 체크
			for (String key : validatorResult.keySet()) { // 실패한 필드명이 들어있는 key값만 꺼내 String key로 저장.
				model.addAttribute(key, validatorResult.get(key)); // 키에 해당하는 값을 꺼낸 뒤, key와 value 모두 저장
			}
			// 입력값 형식이 맞지 않을 때
			// return "";
		}

		// 아이디 중복체크
		if (idCheck(member.getEmail())) {
			// 중복이 있을 때
			// return "";
		}

		// 회원 추가
		// memberService.saveMember(member);
		return "";
	}

	/**
	 * 아이디(이메일) 중복 체크
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET) // 테스트용
	public boolean idCheck(String email) {

		Optional<Member> member = memberService.findByEmail(email);
		if (member.isPresent()) {
			return true;
		}
		return false;
	}

	/**
	 * 회원 탈퇴
	 */
	public void deleteMember(@PathVariable Long memberId, Member member) {
		member.setState(0);
		memberService.deleteMember(member);
	}

	/**
	 * 회원정보 조회
	 */
	@GetMapping("/selectMemberInfo")
	public String selectMember(Long memberId) {
		Optional<Member> member = memberService.selectMemberInfo(memberId);

		return "";
	}

	/**
	 * 회원정보 수정 + 프로필 이미지
	 */
	@PutMapping("/updateMemberInfo")
	public String updateMember(Member member) {

		memberService.updateMemberInfo(member);

		return "";
	}

	/**
	 * 회원의 강의목록 조회
	 */
	@GetMapping("/selectClass")
	public String selectMemberClass(@PathVariable Long memberId) {

		Optional<Member> member = memberService.selectMemberClassRoom(memberId);

		return "";
	}

}
