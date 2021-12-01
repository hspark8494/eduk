package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

}
