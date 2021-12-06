package com.eduk.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduk.domain.ClassRoom;


public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>{
}