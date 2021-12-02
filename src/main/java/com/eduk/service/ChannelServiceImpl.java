package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eduk.domain.Channel;
import com.eduk.repository.ChannelRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

	private final ChannelRepository channelRepository;
	
	@Override
	public void createChannel(Channel channel) {
		channelRepository.save(channel);
	}

	@Override
	public List<Channel> selectChannel() {
		
		return channelRepository.findAll();
	}

	@Override
	public void deleteChannel(Long classRoomId) {
		channelRepository.deleteById(classRoomId);
	}

}
