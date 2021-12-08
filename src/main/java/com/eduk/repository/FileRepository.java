package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.Files;

public interface FileRepository extends JpaRepository<Files, Long> {
	
	@Query(value="select * from files where post_id = ?1", nativeQuery = true)
	Files findByPostId(Long postId);
}
