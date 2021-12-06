package com.eduk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
	private String fileName;
	private String fileOriginalName;
	private String fileDownloadUri; 
	private String fileExt; 
	private long size;

	public FileUpload(String fileName, String fileOriginalName, String fileExt, String fileDownloadUri) {
		this.fileName = fileName; 
		this.fileOriginalName = fileOriginalName;
		this.fileExt = fileExt; 
		this.fileDownloadUri = fileDownloadUri;
	}

	
}
