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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDrive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileDriveId;
	
	@ManyToOne
	@JoinColumn(name="filesId")
	private Files files;
	
	@ManyToOne
	@JoinColumn(name = "referenceRoomId")
	private ReferenceRoom referenceRoom;
}
