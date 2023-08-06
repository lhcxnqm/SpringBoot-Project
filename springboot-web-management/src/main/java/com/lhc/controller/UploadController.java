package com.lhc.controller;

import com.lhc.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 文件上传，本方法一般用于本地存储
     * @param username
     * @param age
     * @param image
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传:{}, {}, {}", username, age, image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //构造唯一文件名- uuid（通用唯一标识码）
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString()+extname;
        //将文件存储在服务器的磁盘目录中 E:\images
        image.transferTo(new File("E:/images/" + newFilename));
        return Result.success();
    }
}
