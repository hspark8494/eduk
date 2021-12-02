package com.eduk.service;

import java.util.List;

import com.eduk.domain.ReferenceRoom;

public interface ReferenceRoomService {
	/**
	 * 자료실 전체 검색
	 */
	List<ReferenceRoom> selectAll();
	
	/**
	 * 자료실 상세 검색
	 */
	
	/**
	 * 자료실 등록
	 */
	void insert(ReferenceRoom referenceRoom);
	
	/**
	 * 자료실 삭제
	 */
	void delete(Long referenceRoomId);
}
