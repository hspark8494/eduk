package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
