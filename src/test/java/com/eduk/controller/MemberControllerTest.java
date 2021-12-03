package com.eduk.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberControllerTest {

	@Test
	void testIdCheck() {
		MemberController controller = new MemberController();
		controller.idCheck("park@naver.com");
	}


}
