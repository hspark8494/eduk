package com.eduk.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements Serializable{ //@size
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message="이메일 형식에 맞지 않습니다.")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="비밀번호는 필수 입력 값입니다.")
//	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
//            message = "비밀번호는 영문 대문자, 소문자, 숫자, 특수문자 중 최소 1가지 이상 포함된 문자조합 8-16자로 입력해주세요.")	
	private String password;
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String name;
	
	//1 - 활동상태, 0 - 탈퇴
	private int state;
	
	@CreationTimestamp
	private LocalDateTime createTime;
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	private String profileImage;
	
	private String introduction;

	public Member(Long memberId) {
		this.memberId = memberId;
	}
	
	public Member(Long memberId, String email, String name, String password, String introduction, String profileImage) {
		this.memberId = memberId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.introduction = introduction;
		this.profileImage = profileImage;
	}
}
