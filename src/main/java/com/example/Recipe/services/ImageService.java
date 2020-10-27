package com.example.Recipe.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ImageService {

	void saveImageFile(Long valueOf, MultipartFile file);

}
