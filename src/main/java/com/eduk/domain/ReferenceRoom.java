package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne
	@JoinColumn(name="channelId")
	private Channel channel;
}
