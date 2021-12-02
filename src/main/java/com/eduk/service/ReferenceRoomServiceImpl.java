package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.ReferenceRoom;
import com.eduk.repository.ReferenceRoomRepository;

@Service
@Transactional
public class ReferenceRoomServiceImpl implements ReferenceRoomService{

	@Autowired
	private ReferenceRoomRepository referenceRoomRepository;
	
	@Override
	public List<ReferenceRoom> selectAll() {
		return referenceRoomRepository.findAll();
	}

	@Override
	public void insert(ReferenceRoom referenceRoom) {
		referenceRoomRepository.save(referenceRoom);
	}

	@Override
	public void delete(Long referenceRoomId) {
		ReferenceRoom dbReferenceRoom = referenceRoomRepository.findById(referenceRoomId).orElse(null);
		if(dbReferenceRoom == null) throw new RuntimeException("자료실 번호 오류로 삭제할 수 없습니다.");
		
		referenceRoomRepository.deleteById(referenceRoomId);
	}

}
