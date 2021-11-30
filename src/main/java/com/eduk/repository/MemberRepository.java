package com.eduk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	/**
	 * 이메일 중복 체크
	 */
	
	public Member findByEmail(String email);
	
	/**
	 * 회원 등록 
	 */
	
}
