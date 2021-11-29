package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {

	@Id
	private long authId;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	

}
