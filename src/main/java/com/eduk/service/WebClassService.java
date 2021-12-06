package com.eduk.service;

import com.eduk.domain.WebClass;

public interface WebClassService {
	WebClass createWebClass(WebClass webClass);
	
	WebClass updateWebClass(WebClass webClass);
	
	void deleteWebClass(Long webClassId);
}
