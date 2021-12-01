package com.eduk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Board;
import com.eduk.service.BoardService;

@RestController
@RequestMapping("/{channelId}/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 게시판 등록
	 */
	@PostMapping("/insert")
	public String insert(Board board) {
		boardService.insert(board);
		
		return "";
	}
	
	/**
	 * 게시판 전체 검색
	 */
	@GetMapping("/list")
	public void list() {
		boardService.selectAll();
	}
	
	/**
	 * 게시판 삭제
	 */
	@DeleteMapping("/delete")
	public String delete(@PathVariable Long boardId) {
		boardService.delete(boardId);
		
		return "";
	}
}
