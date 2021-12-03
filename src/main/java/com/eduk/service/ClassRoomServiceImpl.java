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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClassRoom(Long classRoomId) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
/*
@Service
@RequiredArgsConstructor
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService{

	
	//강의 생성하기
	@Override
	public void regClassRoom(ClassRoom classRoom) {
		ClassRoom classRoom = ClassRoomRepository.save(classRoom);
		System.out.println(classRoom.getClassRoomName());
		
	}
	//강의 설정하기
	@Override
	public ClassRoom updateClassRoom(ClassRoom classRoom) {
		ClassRoom classRoom = classRoomRepository.findbyId(classRoom.getClassRoomId()).orElse(null);
		if(classRoom==null) throw new RuntimeException("강의실번호 오류로 삭제할 수 없습니다.");
		
		classRoom.setClassRoomName(classRoom.getClassRoomName().replace);
		classRoom.setDetail(classRoom.getDetail());
		classRoom.setState(classRoom.getState());
		classRoom.setClassRoomImage(classRoom.getClassRoomImage());
		return classRoom;
	}
	//강의 삭제하기
	@Override
	public void deleteClassRoom(Long classRoomId) {
		ClassRoom classRoom = ClassRoomRepository.findById(classRoomId).orElse(null);
		if(classRoom==null) throw new RuntimeException("강의실번호 오류로 삭제할 수 없습니다.");
		
		ClassRoomRepository.delete(classRoom);
	}
	
	
}*/
