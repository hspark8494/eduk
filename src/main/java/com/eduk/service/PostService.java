package com.eduk.service;

import java.util.List;

import com.eduk.domain.Post;

public interface PostService {
	
	/**
	 * 등록
	 */
	void insert(Post post);
	
	/**
	 * 전체 검색
	 */
	List<Post> selectAll(Long boardId);
	
	/**
	 * 상세 검색
	 */
	
	/**
	 * 수정
	 */
	Post update(Post post);
	
	/**
	 * 삭제
	 */
	void delete(Long postId);
}
