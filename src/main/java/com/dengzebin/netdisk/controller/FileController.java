package com.dengzebin.netdisk.controller;

import com.dengzebin.netdisk.entity.DO.File;
import com.dengzebin.netdisk.service.FileService;
import com.dengzebin.netdisk.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/get_file_list")
    public Result getFileList(@RequestParam("masterId") String masterId){
        List<File> fileList = new ArrayList<>();
        if ("".equals(masterId))
            fileList = fileService.getFileList(null);
        else
            fileList = fileService.getFileList(masterId);
        return Result.success(fileList);
    }

    @PostMapping("/delete_file/{fileId}")
    public Result deleteFile(@PathVariable("fileId") String fileId){
        if (fileService.deleteFile(fileId))
            return Result.success();
        else
            return Result.error(-1,"删除失败！");
    }
}
