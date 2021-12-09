package com.eduk.service;

import java.util.List;

import com.eduk.domain.Files;

public interface FilesService {
	void storeFile(Files files);
	
	Files fileInfo(Long postId);
	
	/**
	 * 파일 전체 목록
	 */
	List<Files> selectAll(Long classRoomId);
}
