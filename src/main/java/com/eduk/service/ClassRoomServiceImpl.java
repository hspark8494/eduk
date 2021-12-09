package com.eduk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.domain.Participant;
import com.eduk.repository.ClassRoomRepository;
import com.eduk.repository.MemberRepository;
import com.eduk.repository.ParticipantRepository;

@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService{
	@Autowired private ClassRoomRepository classRoomRep;
	@Autowired private ParticipantRepository participantRep;
	@Autowired private MemberRepository memberRepo;

	@Override
	public Member getParticipantInfo(Long memberId) {
		Member member = memberRepo.getById(memberId);
		return member;
	}


	@Override
	//@Transactional
	public ClassRoom createWithJoin(ClassRoom classRoom, Member member) {
		classRoom = regClassRoom(classRoom);
		addParticipant(member.getMemberId(), classRoom.getClassRoomId());
		
		return classRoom;
	}
	
	
	@Override
	@Transactional
	public ClassRoom getClassRoom(Long roomId){
		ClassRoom cr = classRoomRep.getById(roomId);
		System.out.println(cr);
		return cr;
	}
	
	@Override
	@Transactional
	public List<Participant> getParticipants(Long roomId){
		return participantRep.findAllByClassRoom(new ClassRoom(roomId));
	}
	
	@Override
	//@Transactional
	public ClassRoom regClassRoom(ClassRoom classRoom) {
		return classRoomRep.save(classRoom);
	}
	
	@Override
	//@Transactional
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
		if(classRoomEntity == null) throw new RuntimeException("강의 번호 오류로 수정할 수 없습니다.");
		classRoomEntity.setClassRoomName(classRoom.getClassRoomName());
			return classRoomEntity;
	}

	@Override
	public void deleteClassRoom(Long classRoomId) {
		ClassRoom classRoomEntity = classRoomRep.findById(classRoomId).orElse(null);
		if(classRoomEntity == null) throw new RuntimeException("강의 번호 오류로 삭제할 수 없습니다.");
		classRoomRep.deleteById(classRoomId);
	}
	
}

