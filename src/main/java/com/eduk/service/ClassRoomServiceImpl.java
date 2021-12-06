package com.eduk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.domain.Participant;
import com.eduk.repository.ClassRoomRepository;
import com.eduk.repository.ParticipantRepository;

@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService{
	
	@Autowired private ClassRoomRepository classRoomRep;
	@Autowired private ParticipantRepository participantRep;
	@Autowired public ClassRoomServiceImpl(ClassRoomRepository classRoomRep) {
		this.classRoomRep = classRoomRep;
	}


	@Override
	@Transactional
	public ClassRoom createWithJoin(ClassRoom classRoom, Member member) {
		classRoom = regClassRoom(classRoom);
		addParticipant(member.getMemberId(), classRoom.getClassRoomId());
		
		return classRoom;
	}
	
	@Override
	@Transactional
	public ClassRoom regClassRoom(ClassRoom classRoom) {
		return classRoomRep.save(classRoom);
	}
	
	@Override
	@Transactional
	public void addParticipant(long memberId, long classRoomId) {
		Participant participant = new Participant();
		participant.setMember(new Member(memberId));
		participant.setClassRoom(new ClassRoom(classRoomId));
		participantRep.save(participant);
	}
	
	@Override
	public List<ClassRoom> findAllByMemberId(Long memberId) {
		return participantRep.findAllByMember(memberId);
	}

	@Override
	public ClassRoom updateClassRoom(ClassRoom classRoom) {
		ClassRoom classRoomEntity = classRoomRep.findById(classRoom.getClassRoomId()).orElse(null);
			return classRoomEntity;
	}

	@Override
	public void deleteClassRoom(Long classRoomId) {
		ClassRoom classRoomEntity = classRoomRep.findById(classRoomId).orElse(null);
		classRoomRep.deleteById(classRoomId);
	}
	
}

