package com.eduk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduk.domain.Files;

public interface FileRepository extends JpaRepository<Files, Long> {
}
