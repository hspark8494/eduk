package com.eduk.security.jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.eduk.domain.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT 토큰의 발행, 공급 및 유효성을 체크하는 클래스
 * 
 * @author 박효승
 *
 */
@Service
public class TokenProvider {
	private final static String SECRET_KEY ="842338B972F2329CD3C8E9C919666";	//서버측 비밀키
	private final static String REFRESH_KEY ="F3DD56F233DF8FDF6C5B33846CF3B";	//리프레쉬 키
	private final static String ISSUER ="eduk";	//토큰 발급자
	
	private final static long TOKEN_TTL = 10000L*60*60; //토큰 만료 기간 (1시간)
	private final static long REFRESH_TOKEN_TTL = 1000L*60*60*24*14; //리프레쉬 토큰 만료 기간 (14일)

	
	/**
	 * JWT 토큰을 발행한다.
	 * Member ID를 Subject로 가지며, Member 객체가 Claim 내부에 저장된다. 
	 * @param member
	 * @return 암호화된 토큰 문자열
	 */
	public String generateToken(Member member) {
		return Jwts.builder()
				.setSubject(member.getEmail())
				.setHeader(createHeader())
				.setClaims(createClaims(member)).setExpiration(createExpireDate(TOKEN_TTL))
				.signWith(SignatureAlgorithm.HS256, createSigningKey(SECRET_KEY)).compact();
	}
	
	/**
	 * JWT 리프레쉬 토큰을 발행한다.
	 * @param member
	 * @return 암호화된 리프레쉬 토큰 문자열
	 */
	public String saveRefreshToken(Member member) {
		return Jwts.builder().setSubject(member.getEmail()).setHeader(createHeader())
				.setClaims(createClaims(member)).setExpiration(createExpireDate(REFRESH_TOKEN_TTL))
				.signWith(SignatureAlgorithm.HS256, createSigningKey(REFRESH_KEY)).compact();
	}

	/**
	 * 토큰의 유효성을 검증한다.
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String token) {
		try {
			Claims accessClaims = getClaimsFormToken(token);
			System.out.println("Access expireTime: " + accessClaims.getExpiration());
			System.out.println("Access userId: " + accessClaims.get("userId"));
			return true;
		} catch (ExpiredJwtException exception) {
			System.out.println("Token Expired UserID : " + exception.getClaims().getSubject());
			return false;
		} catch (JwtException exception) {
			System.out.println("Token Tampered");
			return false;
		} catch (NullPointerException exception) {
			System.out.println("Token is null");
			return false;
		}
	}

	/**
	 * 리프레쉬 유효성을 검증한다.
	 * @param token
	 * @return
	 */
	public boolean isValidRefreshToken(String token) {
		try {
			Claims accessClaims = getClaimsFormToken(token);
			System.out.println("Access expireTime: " + accessClaims.getExpiration());
			System.out.println("Access member: " + accessClaims.get("email"));
			return true;
		} catch (ExpiredJwtException exception) {
			System.out.println("Token Expired MemberID : " + exception.getClaims().getSubject());
			return false;
		} catch (JwtException exception) {
			System.out.println("Token Tampered");
			return false;
		} catch (NullPointerException exception) {
			System.out.println("Token is null");
			return false;
		}
	}
	

	private Date createExpireDate(long expireDate) {
		long curTime = System.currentTimeMillis();
		return new Date(curTime + expireDate);
	}

	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();

		header.put("typ", "accessToken");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());

		return header;
	}

	private Map<String, Object> createClaims(Member member) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", member.getMemberId());
		return claims;
	}

	private Key createSigningKey(String key) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	}

	public static final Claims getClaimsFormToken(String token) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(token)
				.getBody();
	}
	
	public static final long getIdFromToken(String token) {
		return Long.valueOf(String.valueOf(getClaimsFormToken(token).get("id")));
	}
	
	public static final Long getIdFormHeader(HttpHeaders headers) {
		String token =  headers.get("authorization").get(0);
		return getIdFromToken(token);
	}

}
