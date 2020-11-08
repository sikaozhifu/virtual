package com.school.virtual.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public boolean saveFile(MultipartFile file,String file_path);
}
