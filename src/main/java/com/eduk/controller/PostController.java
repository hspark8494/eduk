package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Board;
import com.eduk.domain.Member;
import com.eduk.domain.Post;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.PostService;

@RestController
@RequestMapping("/{boardId}")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	/**
	 * 게시물 등록
	 */
	@PostMapping("/post")
    public Post insert(@RequestBody Post post, @PathVariable Long boardId, @RequestHeader HttpHeaders headers) {
        Long id = TokenProvider.getIdFormHeader(headers);
        post.setBoard(Board.builder().boardId(boardId).build());
        post.setMember(new Member(id));
        Post dbPost = postService.insert(post);

        return dbPost;
    }
	
	/**
	 * 전체 검색
	 */
	@GetMapping("/post")
	public List<Post> list(@PathVariable Long boardId) {
		List<Post> postList = postService.selectAll(boardId);
		
		return postList;
	}
	
	/**
	 * 상세 검색
	 */
	@GetMapping("/post/{postId}")
	public Post read(@PathVariable Long postId, String flag) {
		boolean state = flag==null;
		
		Post post = postService.selectByPostId(postId, state);
		
		return post;
	}
	
	
	/**
	 * 게시물 수정
	 */
	@PutMapping("/post/{postId}")
	public Post update(@RequestBody Post post, @PathVariable Long postId, @RequestHeader HttpHeaders headers) {
		Long id = TokenProvider.getIdFormHeader(headers);
		
		Post dbPost = postService.update(post);
		
		return dbPost;
	}
	
	/**
	 * 게시물 삭제
	 */
	@DeleteMapping("/post/{postId}")
	public void delete(@PathVariable Long postId, @RequestHeader HttpHeaders headers) {
		Long id = TokenProvider.getIdFormHeader(headers);
		
		postService.delete(postId);
	}
	
	/**
	 * 댓글 전체검색
	 */
	
}
