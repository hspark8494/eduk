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

import com.eduk.domain.Member;
import com.eduk.domain.Post;
import com.eduk.domain.Reply;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.ReplyService;

@RestController
@RequestMapping("/{postId}")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	/**
	 * 댓글 or 대댓글 등록
	 */
	@PostMapping("/reply")
	public Reply insert(@RequestBody Reply reply, @PathVariable Long postId, @RequestHeader HttpHeaders headers) {
		Long id = TokenProvider.getIdFormHeader(headers);
		reply.setPost(Post.builder().postId(postId).build());
		reply.setMember(new Member(id));
		
		
		
		Reply dbReply = replyService.insert(reply);
		
		return dbReply;
	}
	
	/**
	 * 댓글 수정
	 */
	@PutMapping("/reply/{replyId}")
	public Reply update(@RequestBody Reply reply, @PathVariable Long replyId, @RequestHeader HttpHeaders headers) {
		Reply dbReply = replyService.update(reply);
		
		return dbReply;
	}
	
	/**
	 * 댓글 삭제
	 */
	@DeleteMapping("/reply/{replyId}")
	public void delete(@PathVariable Long replyId, @RequestHeader HttpHeaders headers) {
		replyService.delete(replyId);
	}
	
	/**
	 * 댓글 전체 검색
	 */
	@GetMapping("/reply")
	public List<Reply> list(@PathVariable Long postId) {
		return replyService.selectAll(postId);
	}
}
