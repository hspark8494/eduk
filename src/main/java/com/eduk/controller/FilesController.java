package com.eduk.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eduk.domain.ClassRoom;
import com.eduk.domain.Files;
import com.eduk.repository.FileRepository;
import com.eduk.service.FilesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FilesController {
	
	private final String SAVE_PATH="C:\\Users\\Lee\\Desktop\\TEST\\save";
	private final FileRepository fileRepository;
	private final FilesService filesService;
	
	/**
	 * 파일 업로드
	 */
	@PostMapping("/uploadfile")
	public Files uploadFile(@RequestPart MultipartFile file, HttpServletRequest request,
			@RequestParam Long postId/* , @RequestParam Long classRoomId */) throws IOException{
		System.out.println("postId : " + postId);
		Files fileEntity = new Files();
		    if(file.getSize()>0) {
		    	String fname=file.getOriginalFilename();
		    	long fsize = file.getSize();
		    	String fileType = file.getContentType();
		    	
		    	String saveDir=request.getServletContext().getRealPath("/save");
		    	fileEntity.setFileName(fname);
		    	fileEntity.setFileSize(fsize);
		    	fileEntity.setFileType(fileType);
		    	fileEntity.setPostId(postId);
		    	fileEntity.setClassRoom(new ClassRoom(42L));
		    	
		    	file.transferTo(new File(saveDir+"/" + fname));
		    	fileRepository.save(fileEntity);
		    }

	    return fileEntity;
	}
	
	/**
	 * 파일 다운로드
	 */
	@GetMapping("/down/{fname}")
	public ModelAndView down(@PathVariable String fname, HttpServletRequest request) {
		String saveDir = request.getServletContext().getRealPath("/save");
		System.out.println("fname : " +fname);
		File file = new File(saveDir+"/"+fname);
		
		return new ModelAndView("downLoadView","fname", file); // 뷰이름이downLoadView ->BeanName찾고 ->UrlBase -> InternalViewResolver
	}
	
	/**
	 * 파일 정보 가져오기
	 * */
	@GetMapping("/info/{postId}")
	public Files fileInfo(@PathVariable Long postId) {
		Files file = fileRepository.findByPostId(postId);
		System.out.println(file);
		return file;
	}
	
	/**
	 * 파일 전체 목록
	 */
	@GetMapping("/list/{classRoomId}")
	public List<Files> list(@PathVariable Long classRoomId) {
		return filesService.selectAll(classRoomId);
	}
}










