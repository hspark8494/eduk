package com.eduk.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.validation.Errors;

import com.eduk.domain.Member;
import com.eduk.exception.EdukException;

public interface MemberService {
	/**
	 * 회원가입 , 유효성 체크
	 */
	 public Map<String, String> register(Errors errors) throws EdukException;
	 
	 /**
	  * 아이디 중복 체크
	  */
	 public Member findByEmail(String email);
	 
	 /**
	  * 회원 등록
	  */
	 public int saveMember(Member member);

}
