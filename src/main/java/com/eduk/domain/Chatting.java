package com.eduk.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chatting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long chattingId; //채팅번호 
	
	private String content;
	
	@CreationTimestamp
	private LocalDateTime chatTime;
	
	@ManyToOne
	@JoinColumn(name="chatRoomId")
	private ChatRoom chatRoom;
}
