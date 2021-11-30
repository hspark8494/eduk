package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
