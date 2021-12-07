package com.eduk.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	  private String name;
	  private MultipartFile file; //폼에서 name="file"
	  private String fileName;
	  
	  private long fileSize;
}


