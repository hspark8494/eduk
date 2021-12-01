package com.eduk.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduk.domain.Member;
import com.eduk.domain.Token;
import com.eduk.repository.MemberRepository;
import com.eduk.repository.TokenRepository;
import com.eduk.security.jwts.TokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * 회원 로그인 및 인증을 관리하는 서비스
 * 
 * @author 박효승
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberAuthServiceImpl implements MemberAuthService{
	private final MemberRepository memberRepository;
	private final TokenProvider tokenProvider;
	private final TokenRepository tokenRep;
	private final PasswordEncoder passwordEncoder;

	public Optional<Member> findByEmail(String email) {

		return memberRepository.findByEmail(email);
	}

	//TODO 예외처리
	/**
	 * 회원가입 메소드
	 * 
	 * @param member
	 * @return
	 */
	@Transactional
	public Map<String, Object> register(Member member) {
		
		String encodedPwd = passwordEncoder.encode(member.getPassword()); // 비밀번호 암호화
		member.setPassword(encodedPwd); 
		
		member = memberRepository.save(member); //회원 등록
		

		return generateMeberMap(member); //회원정보, 토큰, 리프레쉬 토큰을 클라이언트에게 전달
	}

	/**
	 * 로그인 메소드
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> login(Member member) throws Exception{
		Optional<Member> op = memberRepository.findByEmail(member.getEmail());
		Member dbMember = op.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
		
		boolean matched = passwordEncoder.matches(member.getPassword(), dbMember.getPassword());	//비밀번호 대조
		
		if(!matched) {
			throw new UsernameNotFoundException("비밀번호가 일치하지 않습니다.");
		}
		
		return generateMeberMap(dbMember);
	}

	
	private Map<String, Object> generateMeberMap(Member member){
		Map<String, Object> respMap = new HashMap<String, Object>();	//JSON 변환을 위한 HashMap 생성

		respMap.put("member", member);
		
		Token token = Token.builder().member(member).refreshToken(tokenProvider.saveRefreshToken(member)).build(); //리프레쉬 토큰 발행
		tokenRep.save(token); //리프레쉬 토큰 저장
		respMap.put("accessToken", tokenProvider.generateToken(member)); //엑세스 토큰 발행
		respMap.put("refreshToken", token.getRefreshToken());
		
		//TODO : 비밀번호, 기타 정보가 클라이언트에게 전송되지 않게 바꿀 것
		return respMap;
	}

}
