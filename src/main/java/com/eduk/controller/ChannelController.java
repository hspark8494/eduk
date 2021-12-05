package com.eduk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.ClassRoom;
import com.eduk.domain.Member;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.ChannelService;
import com.eduk.service.ChannelServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{classRoomId}")
public class ChannelController {
	
	//private final ChannelServiceImpl channelServiceImpl;
	private final ChannelService channelService;
	
	/**
	 * 채널목록 
	 * */
	@GetMapping("/channel/all")
	public List<Channel> selectChannel(){
		List<Channel> channelList =  channelService.selectChannel();
		return channelList;
	} 
	
	/**
	 * 채널추가  
	 * */
	@PostMapping("/channel")
	public void createChannel(@RequestBody Channel channel, @PathVariable Long classRoomId) {
		channel.setClassRoom(ClassRoom.builder().classRoomId(classRoomId).build());
		channelService.createChannel(channel);
	}
//	@PostMapping("/channel")
//	public ResponseEntity<Map<String, Object>> createClassRoom(@RequestBody Channel channel, @RequestHeader HttpHeaders headers){
//		System.out.println(headers);
//		Long id = TokenProvider.getIdFormHeader(headers);
//		System.out.println(id);
//	    channel.setClassRoom(ClassRoom.builder().classRoomId(classRoomId).build());
//		Channel newChannel = channelServiceImpl.createChannel(channel, new Member(id));
//		
//		Map<String, Object> respMap = new HashMap<String, Object>();
//		respMap.put("newChannel", newChannel);
//		
//		return new ResponseEntity<Map<String,Object>>(respMap, HttpStatus.OK);
//	}
	
	
	/**
	 * 채널삭제 
	 * */
	@DeleteMapping("/channel/{channelId}")
	public void deleteChannel(@PathVariable Long classRoomId) {
		channelService.deleteChannel(classRoomId);
	}
}








