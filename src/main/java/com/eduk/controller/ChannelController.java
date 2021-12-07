package com.eduk.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.ClassRoom;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.ChannelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{classRoomId}")
public class ChannelController {
	
	//private final ChannelServiceImpl channelServiceImpl;
	private final ChannelService channelService;
	
	/**
	 * 채널추가  
	 * */
	@PostMapping("/channel")
	public Channel createChannel(
			@RequestBody Channel channel, 
			@PathVariable Long classRoomId,
			@RequestHeader HttpHeaders headers
			) {
		System.out.println("채널 생성" + channel);
		Long id = TokenProvider.getIdFormHeader(headers);
		channel.setClassRoom(ClassRoom.builder().classRoomId(classRoomId).build());
		//Member member = new Member(id);
		Channel channelEntity = channelService.createChannel(channel);
		return channelEntity;
	}
	
	/**
	 * 채널목록 
	 * */
	@GetMapping("/channel/all")
	public List<Channel> selectChannel(){
		List<Channel> channelList =  channelService.selectChannel();
		return channelList;
	} 
	
	
	/**
	 * 채널수정 
	 * */
	@PutMapping("/channel/{channelId}")
	public Channel updateChannel(@RequestBody Channel channel, @PathVariable Long channelId) {
		Channel channelEntity = channelService.updateChannel(channel);
		return channelEntity;
	}

	/**
	 * 채널삭제 
	 * */
	@DeleteMapping("/channel/{channelId}")
	public void deleteChannel(@PathVariable Long channelId) {
		channelService.deleteChannel(channelId);
	}
}








