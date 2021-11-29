package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
