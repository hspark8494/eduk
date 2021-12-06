package com.eduk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	/**
	 * 조회수 증가
	 * */
	@Query(value = "update post p set p.readnum = p.readnum+1 where p.post_id=?1", nativeQuery = true)
	@Modifying
	int readnumUpdate(Long postId);
	
	/**
	 * boardId에 해당하는 post 조회
	 */
	@Query(value= "select * from post where board_id =?1", nativeQuery = true )
	Page<Post> selectByBoardId(Pageable pageable, Long boardId);
}
