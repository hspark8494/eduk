package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

	@Id
	private Long channelId;
	
	private Long channelTypeId;
	
	private Long classRoomId;
	
	private int state;
	
	private Long readAuth;
	
	private Long writeAuth;
	
	private Long deleteAuth;
}
