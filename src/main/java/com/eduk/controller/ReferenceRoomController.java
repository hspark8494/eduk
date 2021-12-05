package com.eduk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.ReferenceRoom;
import com.eduk.service.ReferenceRoomService;

@RestController
@RequestMapping("/{channelId}")
public class ReferenceRoomController {
	
	@Autowired
	private ReferenceRoomService referenceRoomService;
	
	/**
	 * 자료실 전체 검색
	 */
	@GetMapping("/referenceroom")
	public List<ReferenceRoom> list() {
		List<ReferenceRoom> referenceRoomList = referenceRoomService.selectAll();
		
		return referenceRoomList;
	}
	
	/**
	 * 자료실 상세 검색
	 */
	@GetMapping("/referenceroom/{referenceRoomId}")
	public ReferenceRoom read(@PathVariable Long referenceRoomId) {
		ReferenceRoom referenceRoom = referenceRoomService.selectByReferenceRoomId(referenceRoomId);
		
		return referenceRoom;
	}
	
	/**
	 * 자료실 등록
	 */
	@PostMapping("/referenceroom")
	public void insert(@RequestBody ReferenceRoom referenceRoom, @PathVariable Long channelId) {
		referenceRoom.setChannel(Channel.builder().channelId(channelId).build());
		referenceRoomService.insert(referenceRoom);
	}
	
	/**
	 * 자료실 삭제
	 */
	@DeleteMapping("/referenceroom/{referenceRoomId}")
	public void delete(@PathVariable Long referenceRoomId) {
		referenceRoomService.delete(referenceRoomId);
	}
}
