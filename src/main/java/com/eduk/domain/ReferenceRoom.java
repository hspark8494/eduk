package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReferenceRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long referenceRoomId;
	
	private String referenceRoomName;
}
