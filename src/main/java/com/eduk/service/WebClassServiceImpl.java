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
	public WebClass createWebClass(WebClass webClass) {
		return webClassRepository.save(webClass);
	}

	@Override
	public WebClass updateWebClass(WebClass webClass) {	
		WebClass webClassEntity = webClassRepository.findById(webClass.getWebClassId()).orElse(null);
		if(webClassEntity==null) throw new RuntimeException("수정할 수 없습니다.");
		webClassEntity.setWebClassName(webClass.getWebClassName());
		return webClassEntity;
	}

	@Override
	public void deleteWebClass(Long webClassId) {
		WebClass webClassEntity = webClassRepository.findById(webClassId).orElse(null);
		if(webClassEntity==null) throw new RuntimeException("삭제할 수 없습니다.");
		webClassRepository.deleteById(webClassId);
		
	}
	

}
