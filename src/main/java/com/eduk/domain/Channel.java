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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long channelId;
	
	private String channelType;
	
	private int state;
	
	@ManyToOne
	@JoinColumn(name="classRoomId")
	private ClassRoom classRoom;
	
	@OneToOne
	@JoinColumn(name="authId")
	private Authentication authentication;
	
	public Channel(Long channelId) {
		this.channelId = channelId;
	}
	
}
