package com.eduk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Post;
import com.eduk.domain.Reply;
import com.eduk.service.ReplyService;

@RestController
@RequestMapping("/{postId}/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	/**
	 * 댓글 등록
	 */
	@PostMapping("/insert")
	public void insert(Reply reply, @PathVariable Long postId) {
		reply.setPost(Post.builder().postId(postId).build());
		replyService.insert(reply);
	}
	
	/**
	 * 댓글 수정
	 */
	@PutMapping("/update/{replyId}")
	public Reply update(Reply reply, @PathVariable Long replyId) {
		Reply dbReply = replyService.update(reply);
		
		return dbReply;
	}
	
	/**
	 * 댓글 삭제
	 */
	@DeleteMapping("/delete/{replyId}")
	public void delete(@PathVariable Long replyId) {
		replyService.delete(replyId);
	}
}
