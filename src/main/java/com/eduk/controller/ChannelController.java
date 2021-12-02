package com.eduk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.ClassRoom;
import com.eduk.service.ChannelServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{classRoomId}")
public class ChannelController {
	
	private final ChannelServiceImpl channelServiceImpl;
	
	/**
	 * 채널목록 
	 * */
	@GetMapping("/channel/all")
	public List<Channel> selectChannel(){
		List<Channel> channelList =  channelServiceImpl.selectChannel();
		return channelList;
	} 
	
	/**
	 * 채널추가  
	 * */
	@PostMapping("/channel")
	public void createChannel(@RequestBody Channel channel, @PathVariable Long classRoomId) {
		channel.setClassRoom(ClassRoom.builder().classRoomId(classRoomId).build());
		channelServiceImpl.createChannel(channel);
	}
	
	/**
	 * 채널삭제 
	 * */
	@DeleteMapping("/channel/{channelId}")
	public void deleteChannel(@PathVariable Long classRoomId) {
		channelServiceImpl.deleteChannel(classRoomId);
	}
}








