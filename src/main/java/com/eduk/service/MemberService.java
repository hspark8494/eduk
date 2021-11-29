package com.eduk.service;

import com.eduk.domain.Member;
import com.eduk.exception.EdukException;

public interface MemberService {
	void register(Member member) throws EdukException;
}
