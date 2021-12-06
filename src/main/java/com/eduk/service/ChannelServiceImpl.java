package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eduk.domain.Channel;
import com.eduk.domain.WebClass;
import com.eduk.repository.ChannelRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

	private final ChannelRepository channelRepository;
	
	@Override
	public Channel createChannel(Channel channel) {
		return channelRepository.save(channel);
	}

	@Override
	public List<Channel> selectChannel() {
		
		return channelRepository.findAll();
	}

	@Override
	public Channel updateChannel(Channel channel) {
		Channel channelEntity = channelRepository.findById(channel.getChannelId()).orElse(null);
		if(channelEntity==null) throw new RuntimeException("수정할 수 없습니다.");
		channelEntity.setChannelType(channel.getChannelType());
		
		return channelEntity;
	}
	
	@Override
	public void deleteChannel(Long channelId) {
		Channel channelEntity = channelRepository.findById(channelId).orElse(null);
		if(channelEntity==null) throw new RuntimeException("삭제할 수 없습니다.");
		
		channelRepository.deleteById(channelId);
	}


}
