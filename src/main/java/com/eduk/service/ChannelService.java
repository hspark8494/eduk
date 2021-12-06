package com.eduk.service;

import java.util.List;

import com.eduk.domain.Channel;

public interface ChannelService {
	Channel createChannel(Channel channel);
	
	List<Channel> selectChannel();
	
	Channel updateChannel(Channel channel);
	
	void deleteChannel(Long channelId);
}
