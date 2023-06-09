package com.dengzebin.netdisk.controller;

import com.dengzebin.netdisk.entity.DO.File;
import com.dengzebin.netdisk.service.FileService;
import com.dengzebin.netdisk.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DengZebing
 * @Description TODO
 * @Date 2023/5/30 15:01
 */

@RestController
@RequestMapping("/netDisk")
public class FileController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/create_file")
    public Result createFile(@RequestBody File file) throws Exception {
        if (!fileService.createFile(file)){
            throw new Exception("上传失败！");
        }
        return Result.success(file);
    }
}
