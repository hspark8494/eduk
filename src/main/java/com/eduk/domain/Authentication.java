package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authId;
	
	private String authName;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	

}
