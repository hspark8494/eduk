package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Board;
import com.eduk.domain.Channel;
import com.eduk.domain.Member;
import com.eduk.domain.Post;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.BoardService;

@RestController
@RequestMapping("/{channelId}")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 게시판 등록
	 */
	@PostMapping("/board")
	public Board insert(@RequestBody Board board, @PathVariable Long channelId) {
		board.setChannel(Channel.builder().channelId(channelId).build());
		Board dbBoard = boardService.insert(board);
		
		return dbBoard;
	}
	
	/**
	 * 게시판 전체 검색
	 */
	@GetMapping("/board")
	public List<Board> list(@PathVariable Long channelId) {
		List<Board> board = boardService.selectAll(channelId);
		
		return board;
	}
	
	/**
	 * 상세 검색
	 */
	@GetMapping("/board/{boardId}")
	public Board read(@PathVariable Long boardId) {
		Board board = boardService.selectByBoardId(boardId);
		
		return board;
	}
	
	/**
	 * 게시판 삭제
	 */
	@DeleteMapping("/board/{boardId}")
	public void delete(@PathVariable Long boardId) {
		boardService.delete(boardId);
	}
}
