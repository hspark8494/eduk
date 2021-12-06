package com.eduk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	/**
	 * postId에 해당하는 reply 조회
	 */
	@Query(value= "select * from reply where post_id =?1", nativeQuery = true )
	List<Reply> selectByPostId(Long postId);
}
