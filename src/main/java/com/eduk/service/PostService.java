package com.eduk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eduk.domain.Post;

public interface PostService {
	
	/**
	 * 등록
	 */
	Post insert(Post post);
	
	/**
	 * 전체 검색
	 */
	Page<Post> selectAllById(Pageable pageable, Long boardId);
	
	/**
	 * 상세 검색
	 * 		: 조회수 증가 - state가 true면 조회수 증가
	 */
	Post selectByPostId(Long postId, boolean state);
	
	/**
	 * 수정
	 */
	Post update(Post post);
	
	/**
	 * 삭제
	 */
	void delete(Long postId);
}
