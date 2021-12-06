package com.eduk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	/**
	 * channelId에 해당하는 board 조회
	 */
	@Query(value= "select * from board where channel_id =?1", nativeQuery = true )
	List<Board> selectByChannelId(Long channelId);
}
