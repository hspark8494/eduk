package com.eduk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eduk.domain.Board;
import com.eduk.domain.Post;
import com.eduk.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	/**
	 * 게시물 등록
	 */
	@PostMapping("/insert")
	public String insert(Post post, Long boardId) {
		
		//부모글번호를 Post에 세팅
		post.setBoard(Board.builder().boardId(boardId).build());

		postService.insert(post);
		
		return "";
	}
	
	/**
	 * 전체 검색
	 */
	@GetMapping("/list")
	public void list(Long boardId) {
		//model.addAttribute("boardId", boardId); //부모글번호
		postService.selectAll(boardId);
	}
	
	/**
	 * 상세 검색
	 */
	
	
	/**
	 * 게시물 수정
	 */
	@PutMapping("/update")
	public ModelAndView update(Post post) {
		Post dbPost = postService.update(post);
		
		return new ModelAndView("", "post", dbPost);
	}
	
	
	/**
	 * 게시물 삭제
	 */
	@DeleteMapping("/delete")
	public String delete(Long postId) {
		postService.delete(postId);
		
		return "";
	}
}