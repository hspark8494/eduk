package com.eduk.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileId;
	
	private String fileName;
	
	private Long fileSize;
	
	private String fileType;
	
	@CreationTimestamp
	private LocalDateTime createTime;
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	@ManyToOne
	@JoinColumn(name="classRoomId")
	private ClassRoom classRoom;
	
	@ManyToOne
	@JoinColumn(name="chattingId")
	private Chatting chatting;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	
	private Long postId;
	
	public Files(String fileName) {
		this.fileName=fileName;
	}
	
	public Files(String fileName, Long fileSize) {
		this.fileName=fileName;
		this.fileSize=fileSize;
	}
	
}
