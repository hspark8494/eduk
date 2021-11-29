package com.eduk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Member;
import com.eduk.exception.EdukException;
import com.eduk.repository.MemberRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired private MemberRepository memberRep;

	@Override
	public void register(Member member) throws EdukException {
		//TODO : 유효성 체크
		//TODO : 중복성 체크
		memberRep.save(member);
	}

}
