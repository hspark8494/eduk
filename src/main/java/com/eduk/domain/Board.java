package com.eduk.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Board {//게시판

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long boardId;
	
	private int state;
	
	private String boardName;
	
	@ManyToOne
	@JoinColumn(name="channelId")
	private Channel channel;
	
//	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
//	 private List<Post> postList;

}
