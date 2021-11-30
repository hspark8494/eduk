package com.eduk.domain;

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
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scheduleId;
	
	private String title;
	
	private String scheduleDate;
	
	private String color;
	
	@ManyToOne
	@JoinColumn(name="channelId")
	private Channel channel;
}
