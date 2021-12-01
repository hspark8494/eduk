package com.eduk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 토큰 갱신을 위한 리프레쉬 토큰을 저장해두는 엔티티
 * 
 * @author 박효승
 *
 */
@Getter
@RequiredArgsConstructor
@Table
@Entity
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tokenId;

	@Column(length = 2000)
	private String refreshToken;

	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;

	@Builder
	public Token(String refreshToken, Member member) {
		this.refreshToken = refreshToken;
		this.member = member;
	}

	/**
	 * 토큰 갱신
	 * 
	 * @param refreshToken
	 */
	public void refreshUpdate(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
