package com.eduk.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eduk.domain.Files;

@RestController
@RequestMapping("/file")
public class FilesController {
	
	/**
	 * 파일 업로드
	 */
	@PostMapping("/uploadfile")
	public void uploadFile(@RequestParam("file") MultipartFile file) { 
		if(file.isEmpty()){ //파일 업로드가 안됐을 시 처리 
			
//		}
//			String fileName = fileStorageService.storeFile(file); 
//			//확장자만 추출하는 형태 ex) exe , png, jpg ... 
//			String fileExt = fileName.replaceAll("^.*\\.(.*)$", "$1"); 
//			String fileOriginalName = StringUtils.cleanPath(file.getOriginalFilename()); 
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//					.path("/file/downloadfile/")
//					.path(fileName)
//					.toUriString(); 
//			return new File(fileName, fileOriginalName, fileExt, fileDownloadUri); 
			}
	}
	
	/**
	 * 파일 다운로드
	 */
	@GetMapping("/download")
	public void download() {
		
	}
}
