package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long chatRoomId;
	
	private int state;
	
	private String chatRoomName;
	
	@ManyToOne
	@JoinColumn(name="channelId")
	private Channel channel;
	
	@OneToOne
	@JoinColumn(name="webClassId")
	private WebClass webClass;
}
