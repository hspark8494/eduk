package com.eduk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduk.domain.ReferenceRoom;

public interface ReferenceRoomRepository extends JpaRepository<ReferenceRoom, Long>{
	
	/**
	 * channelId에 해당하는 referenceRoom 조회
	 */
	@Query(value= "select * from reference_room where channel_id =?1", nativeQuery = true )
	List<ReferenceRoom> selectByChannelId(Long channelId);
}
