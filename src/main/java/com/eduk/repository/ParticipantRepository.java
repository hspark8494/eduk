package com.eduk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
	@Query("select p.classRoom from Participant p where p.member.id = ?1")
	List<ClassRoom> findAllByMember(Long memberId);
	
	List<Participant> findAllByClassRoom(ClassRoom classRoom);
	
	
	//@Query("select p from Participant p where p.member.id = ?1 and p.classRoom.id = 2?")
	//Participant findByMemberAndClassRoom(Long memberId, Long classRoomId);
}
