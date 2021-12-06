package com.eduk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eduk.domain.Member;

public interface StudentManagementService {
	List<Member>selectMember();
	
	Page<Member>selectMember(Pageable pageable);
	
	Member selectByMemberId(Long memberId);
	
	Member updateMember(Member member);

	void deleteMember(Long memberId);
	
}
