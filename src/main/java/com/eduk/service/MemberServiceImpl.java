package com.eduk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.exception.EdukException;
import com.eduk.repository.MemberRepository;
import com.eduk.repository.ParticipantRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRep;
	
	@Autowired
	private ParticipantRepository participantRep;
	

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
	 * 이메일 유효성 검사
	 */
	public boolean isEmail(String str) {
	    return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", str);
	}
	
	/**
	 * 비밀번호 유효성 검사
	 * 특수문자, 영문, 숫자 조합 (8~10 자리)
	 */
	public boolean isPassword(String str) {
	    return Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,10}$", str);
	}
	
	/**
	 * 이메일 중복 체크
	 * @return 
	 */
	public Optional<Member> findByEmail(String email) {
		
		Optional<Member> member = memberRep.findByEmail(email);
		
		System.out.println(member.get().toString());
		
		
		return member;
	}

	/**
	  * 회원 등록
	  */
	@Override
	public void saveMember(Member member) {
		
		memberRep.save(member);
	}

	/**
	 * 회원 정보 조회
	 */
	@Override
	public Optional<Member> selectMemberInfo(Long memberId) {
		
		return memberRep.findById(memberId);
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateMemberInfo(Member member) {
		
		memberRep.save(member);
		
	}

	/**
	 * 회원의 강의목록 조회
	 * @return 
	 */
	@Override
	public List<ClassRoom> selectMemberClassRoom(Long memberId) {
		
		List<ClassRoom> classRoom = participantRep.findAllByMember(memberId);
		
		return classRoom;
		
	}

	/**
	 * 회원 탈퇴
	 */
	@Override
	public void deleteMember(Member member) {
		
		memberRep.save(member);
		
	}

}
