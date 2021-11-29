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
public class ChatRoom {

	@Id
	private Long chatRoomId;
	
	private Long channelId;
	
	private int state;
	
	private String chatRoomName;
}
