package ua.com.shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		WINE
	}
	
	boolean write(Folder folder, MultipartFile file, int id);
}
