package com.eduk.service;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.eduk.domain.Member;
import com.eduk.exception.EdukException;
import com.eduk.repository.MemberRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired 
	private MemberRepository memberRep;

	//유효성 체크
	@Override
	public Map<String, String> register(Errors errors) throws EdukException {
		
		//회원가입 시, 유효성 체크
		Map<String, String> validatorResult = new HashedMap();
				
		for (FieldError error : errors.getFieldErrors()) { //유효성 검사에 실패한 필드 목록 가져옴.
			String validKeyName = String.format("valid_%s", error.getField()); //유호성 검사에 실패한 필드명 가져옴.
			validatorResult.put(validKeyName, error.getDefaultMessage()); //유효성 검사에 실패한 필드에 정의된 메세지를 가져온다.
		}
		
		return validatorResult;
		
		//TODO : 중복성 체크
		//memberRep.save(member);
	}
	
	/**
	 * 이메일 중복 체크
	 */
	public Member findByEmail(String email) {
		Member member = memberRep.findByEmail(email);
		
		return member;
	}

	/**
	  * 회원 등록
	  */
	@Override
	public int saveMember(Member member) {
		
		memberRep.save(member);
		return 0;
	}
	
	

}
