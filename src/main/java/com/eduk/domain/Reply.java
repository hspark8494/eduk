package com.eduk.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long replyId;
	
	private String replyContent;
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;
}