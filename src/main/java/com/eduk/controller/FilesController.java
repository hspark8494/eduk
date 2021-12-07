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
import org.springframework.web.servlet.ModelAndView;

import com.eduk.domain.Files;
import com.eduk.dto.FileDTO;

@RestController
@RequestMapping("/file")
public class FilesController {
	
	private final String SAVE_PATH="/Users/sungkyu-kim/springDownload";
	
	/**
	 * 파일 업로드
	 */
	@PostMapping("/uploadfile")
	public FileDTO uploadFile(FileDTO fileDTO, HttpSession session) { 
		//저장할 폴더 설정
	    //String path = session.getServletContext().getRealPath("/WEB-INF/save");
	    
	    MultipartFile file = fileDTO.getFile();
	    if(file.getSize()>0)
		try {
	      file.transferTo(new File(SAVE_PATH+"/" + file.getOriginalFilename()));
	      
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		fileDTO.setFileName(file.getOriginalFilename());
		fileDTO.setOriginalFileName(file.getOriginalFilename());
		fileDTO.setFileSize(file.getSize());
		
		return fileDTO;
	}
	
	/**
	 * 파일 다운로드
	 */
	@GetMapping("/download")
	public ModelAndView download(String fname) {
		File file = new File(SAVE_PATH+"/"+fname);
		return new ModelAndView("downLoadView","fname", file);
	}
}
