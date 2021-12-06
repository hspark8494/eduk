package com.eduk.service;

import java.util.List;

import com.eduk.domain.Board;

public interface BoardService {
	/**
	 * 게시판 등록
	 */
	void insert(Board board);

	/**
	 * 전체검색
	 */
	Board selectAll(Long channelId);
	
	/**
	 * 상세 검색
	 */
	Board selectByBoardId(Long boardId);
	
	/**
	 * 게시판 삭제
	 */
	void delete(Long boardId);
}
