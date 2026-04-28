package org.example.controller;

import org.example.entity.Result;
import org.example.util.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    
    @Autowired
    private AliOssUtil aliOssUtil;
    
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //确保文件名唯一
        originalFilename = System.currentTimeMillis() + "_" + originalFilename;
        String url = aliOssUtil.uploadFile(originalFilename, file.getInputStream());


        return Result.success("上传成功",url);

    }

}
