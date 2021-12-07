package com.eduk.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Participant;
import com.eduk.repository.ParticipantRepository;

@SpringBootTest
class EdukApplicationTests {
	@Autowired ParticipantRepository partRep;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testParti2() {
		List<Participant> list = partRep.findAllByClassRoom(new ClassRoom(173L));
		System.out.println(list.get(0).getMember());
		System.out.println(list.get(0).getClassRoom());
		
	}
}
