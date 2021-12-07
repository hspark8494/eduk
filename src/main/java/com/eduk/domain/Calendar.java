package com.eduk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long calendarId;
	
	@OneToOne
	@JoinColumn(name="channelId")
	private Channel channel;
	
	@Column(length = 3000)
	private String calendarContent;
}
