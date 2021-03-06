package com.eduk.service;

import java.util.List;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.domain.Participant;

public interface ClassRoomService {
	
	Member getParticipantInfo(Long memberId);
	
	ClassRoom regClassRoom(ClassRoom classRoom);
	
	ClassRoom updateClassRoom(ClassRoom classRoom);
	
	void deleteClassRoom(Long classRoomId);
	
	ClassRoom createWithJoin(ClassRoom classRoom, Member member);
	
	void addParticipant(long memberId, long classRoomId);
	
	List<ClassRoom> findAllByMemberId(Long memberId);
	ClassRoom getClassRoom(Long roomId);

	List<Participant> getParticipants(Long roomId);
}