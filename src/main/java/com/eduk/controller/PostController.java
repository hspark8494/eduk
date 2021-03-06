package com.eduk.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Post insert(
    		@RequestBody Post post, 
    		@PathVariable Long boardId, 
    		@RequestHeader HttpHeaders headers
    		){
		System.out.println("post controller call...");
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
	public Map<String, Object> list(@PathVariable Long boardId, @RequestParam(defaultValue = "1") int nowPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Pageable pageable = PageRequest.of((nowPage-1), 10, Direction.DESC, "post_id");
		Page<Post> postList = postService.selectAllById(pageable, boardId);
		
		int blockCount = 5;
		int temp = (nowPage-1)%blockCount;
		int startPage = nowPage-temp;
		
		map.put("postList", postList);
		map.put("blockCount", blockCount);
		map.put("nowPage", nowPage);
		map.put("startPage", startPage);
		
		return map;
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
	public Post update(@RequestBody Post post, @PathVariable Long postId) {
		Post dbPost = postService.update(post, postId);

		return dbPost;
	}
	
	/**
	 * 게시물 삭제
	 */
	@DeleteMapping("/post/{postId}")
	public void delete(@RequestBody Post post, @PathVariable Long postId) {
		postService.delete(postId);
	}
	
}
