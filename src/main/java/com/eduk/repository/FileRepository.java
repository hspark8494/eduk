package com.eduk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Files;

public interface FileRepository extends JpaRepository<Files, Long> {
	
	@Query(value="select * from files where post_id = ?1", nativeQuery = true)
	Files findByPostId(Long postId);
	
	/**
	 * classRoomId에 따른 file 조회
	 */
	@Query(value="select * from files where class_room_id=?1 and post_id is not null", nativeQuery = true)
	List<Files> selectByClassRoomId(Long classRoomId);
}
