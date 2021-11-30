package com.eduk.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eduk.domain.WebClass;
import com.eduk.repository.WebClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WebClassServiceImpl implements WebClassService {
	
	private final WebClassRepository webClassRepository;
	
	@Override
	public void createWebClass(WebClass webClass) {
		webClassRepository.save(webClass);
	}

}
