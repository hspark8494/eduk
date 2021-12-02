package com.eduk.service;

import java.util.List;

import com.eduk.domain.Channel;

public interface ChannelService {
	void createChannel(Channel channel);
	
	List<Channel> selectChannel();
	
	void deleteChannel(Long classRoomId);
}
