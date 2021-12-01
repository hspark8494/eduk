package com.eduk.service;

import java.util.Map;
import java.util.Optional;

import com.eduk.domain.Member;

/**
 * 회원 로그인 및 인증을 관리하는 서비스
 * 
 * @author 박효승
 *
 */

public interface MemberAuthService {
	Optional<Member> findByEmail(String email);
	Map<String, Object> register(Member member);
	Map<String, Object> login(Member member) throws Exception;

}
