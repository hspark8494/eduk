package com.eduk.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eduk.domain.Files;
import com.eduk.domain.Member;

@RestController
//@RequestMapping("/{memberId}")
public class FilesController {
	
	/**
	 * 파일 업로드
	 */
	@PostMapping("/upload")
	public String upload(Files files, @PathVariable Long memberId, HttpSession session) {;
		files.setMember(Member.builder().memberId(memberId).build());
		String path = session.getServletContext().getRealPath("/WEB-INF/save");
	    
	    MultipartFile file = files.getFile();
	    
	    try {
			file.transferTo(new File(path+"/" + file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    files.setFileName(file.getOriginalFilename());
	    files.setFileSize(file.getSize());
	    files.setFileType(file.getContentType());
		
		return "uploadResult";
	}
	
	/**
	 * 파일 다운로드
	 */
	@GetMapping("/download")
	public void download() {
		
	}
}
