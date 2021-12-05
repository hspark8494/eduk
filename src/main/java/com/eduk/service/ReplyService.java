package com.eduk.service;

import com.eduk.domain.Reply;

public interface ReplyService {
	/**
	 * 댓글 등록
	 */
	Reply insert(Reply reply);
	
	/**
	 * 댓글 수정
	 */
	Reply update(Reply reply);
	
	/**
	 * 댓글 삭제
	 */
	void delete(Long replyId);
}
