package com.eduk.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.EmailService;
import com.eduk.service.MemberService;

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

	/*
	 * //@PutMapping("/saveImage")
	 * 
	 * @RequestMapping(value = "/saveImage" , method = RequestMethod.GET) public
	 * Member image(@RequestParam("file") MultipartFile file, @RequestBody Member
	 * member, HttpSession session) throws Exception {
	 * 
	 * //이미지 링크를 memberprofileImage에 저장했을 때
	 * 
	 * String path = session.getServletContext().getRealPath("/WEB-INF/save");
	 * 
	 * try { URL url = new URL(member.getProfileImage());
	 * 
	 * BufferedImage image = ImageIO.read(url); File file = new File(path);
	 * ImageIO.write(image, "jpg", file);
	 * 
	 * member.setProfileImage(member.getMemberId() + "/" + path);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return member; }
	 * 
	 */

	/**
	 * 이미지 저장하기
	 */
	@PostMapping("/saveImage")
	public ResponseEntity createFeed(@RequestParam("file") MultipartFile file,
			@RequestParam Long memberId,
			@RequestParam String email,
			@RequestParam String name,
			@RequestParam String password,
			@RequestParam String introduction,
			@RequestParam String profileImage,
			HttpServletRequest request) {
		System.out.println("saveImage call....");
		
		Member member = new Member(memberId, email, name, password, introduction, profileImage);
		
		// src 주소를 만들어 낸다.
		StringBuilder sb = new StringBuilder();

		// file image 가 없을 경우
		if (file.isEmpty()) {
			sb.append("none"); // 기본 이미지 넣기
		} else {
			sb.append(file.getOriginalFilename()); // 업로드한 파일 명
			//sb.append(member.getProfileImage());
		}
		String saveDir=request.getServletContext().getRealPath("/save");
    	System.out.println(request.getServletContext().getRealPath("/save"));
    	System.out.println(request.getServletContext().getContextPath());
		if (!file.isEmpty()) {
			File dest = new File(saveDir + "/" + sb.toString());
			try {
				file.transferTo(dest); // 원하는 위치에 저장
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			member.setProfileImage(sb.toString()); // db에 멤버 id/링크+파일이름 등록
			updateMember(member);

		}
		return new ResponseEntity<String>(file.getName(), HttpStatus.OK);
	}

	/**
	 * 아이디(이메일) 중복 체크
	 */
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
	public boolean isEmail(@PathVariable String email) {
		if (memberService.isEmail(email)) {
			return true; // 이메일 형식 맞을때
		}
		return false; // 이메일 형식 아닐때

	}

	/**
	 * 비밀번호 유효성 검사 특수문자, 영문, 숫자 조합 (8~10 자리)
	 */
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
	@GetMapping("/selectMemberInfo")
	public Optional<Member> selectMember(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
		Long id = TokenProvider.getIdFormHeader(headers);
		Optional<Member> member = memberService.selectMemberInfo(id);

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
		System.out.println("updateMemberInfo로 넘어옴");
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
