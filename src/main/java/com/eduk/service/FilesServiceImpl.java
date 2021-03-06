package com.eduk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduk.domain.Files;
import com.eduk.repository.FileRepository;

@Service
@Transactional
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public void storeFile(Files files) {
//		Files f = new Files();
//		f.setFileName(files.setFileName());
	}

	@Override
	public Files fileInfo(Long postId) {
		Files file = fileRepository.findById(postId).orElse(null);
		return file;
	}

	@Override
	public List<Files> selectAll(Long classRoomId) {
		List<Files> filesList = fileRepository.selectByClassRoomId(classRoomId);
		
		return filesList;
	}

}
