package com.eduk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long participantId;

	@ManyToOne
	@JoinColumn(name="memberId")
	@NonNull
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="classRoomId")
	@NonNull
	private ClassRoom classRoom;
	
	private int authentication;
	private int state;
}
