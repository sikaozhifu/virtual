package com.school.virtual.service.impl;

import com.school.virtual.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Transactional
    @Override
    public boolean saveFile(MultipartFile file, String file_path) {
        File dest_file = new File(file_path);
        try {
            file.transferTo(dest_file);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("图片保存异常");
        }
        return false;
    }
}
