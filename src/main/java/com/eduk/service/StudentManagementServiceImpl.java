package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eduk.domain.Member;
import com.eduk.repository.MemberRepository;

@Service
@Transactional
public class StudentManagementServiceImpl implements StudentManagementService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public List<Member> selectMember() {
		List<Member>list = memberRepository.findAll();
		return list;
	}
	@Override
	public Page<Member> selectMember(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}
	@Override
	public Member selectByMemberId(Long memberId) {
		Member member=memberRepository.findById(memberId).orElse(null);
		return member;
	}
	@Override
	public void deleteMember(Long memberId) {
		memberRepository.deleteById(memberId);
	}

	

}
