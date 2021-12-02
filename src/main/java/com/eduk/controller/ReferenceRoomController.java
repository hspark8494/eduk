package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.ReferenceRoom;
import com.eduk.service.ReferenceRoomService;

@RestController
@RequestMapping("/{channelId}/referenceroom")
public class ReferenceRoomController {
	
	@Autowired
	private ReferenceRoomService referenceRoomService;
	
	/**
	 * 자료실 전체 검색
	 */
	@GetMapping("/list")
	public List<ReferenceRoom> list() {
		List<ReferenceRoom> referenceRoomList = referenceRoomService.selectAll();
		
		return referenceRoomList;
	}
	
	/**
	 * 자료실 상세 검색
	 */
	
	/**
	 * 자료실 등록
	 */
	@PostMapping("/insert")
	public void insert(ReferenceRoom referenceRoom, @PathVariable Long channelId) {
		referenceRoom.setChannel(Channel.builder().channelId(channelId).build());
		referenceRoomService.insert(referenceRoom);
	}
	
	/**
	 * 자료실 삭제
	 */
	@DeleteMapping("/delete/{referenceRoomId}")
	public void delete(@PathVariable Long referenceRoomId) {
		referenceRoomService.delete(referenceRoomId);
	}
}
