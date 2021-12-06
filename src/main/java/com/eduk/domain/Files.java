package com.eduk.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

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
	private String realName;
	private Long fileSize;
	private String fileType;
	private String scope;
	
	@CreationTimestamp
	private LocalDateTime createTime;
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	@ManyToOne
	@JoinColumn(name="referenceRoomId")
	private ReferenceRoom referenceRoom;
	
	@ManyToOne
	@JoinColumn(name="chattingId")
	private Chatting chatting;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	
}
