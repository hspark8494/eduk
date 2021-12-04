package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eduk.domain.Board;
import com.eduk.domain.Post;
import com.eduk.domain.Schedule;
import com.eduk.service.PostService;

@RestController
@RequestMapping("/{boardId}/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	/**
	 * 게시물 등록
	 */
	@PostMapping("/insert")
	public void insert(@RequestBody Post post, @PathVariable Long boardId) {
		post.setBoard(Board.builder().boardId(boardId).build());
		postService.insert(post);
	}
	
	/**
	 * 전체 검색
	 */
	@GetMapping("/list")
	public List<Post> list(@PathVariable Long boardId) {
		List<Post> postList = postService.selectAll(boardId);
		
		return postList;
	}
	
	/**
	 * 상세 검색
	 */
	@GetMapping("/read/{postId}")
	public Post read(@PathVariable Long postId) {
		Post post = postService.selectByPostId(postId);
		
		return post;
	}
	
	
	/**
	 * 게시물 수정
	 */
	@PutMapping("/update/{postId}")
	public Post update(@RequestBody Post post, @PathVariable Long postId) {
		Post dbPost = postService.update(post);
		
		return dbPost;
	}
	
	/**
	 * 게시물 삭제
	 */
	@DeleteMapping("/delete/{postId}")
	public void delete(@PathVariable Long postId) {
		postService.delete(postId);
	}
}
