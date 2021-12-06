package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Board;
import com.eduk.domain.Channel;

public interface BoardRepository extends JpaRepository<Board, Long>{
	Board findByChannel(Channel channel);
}
