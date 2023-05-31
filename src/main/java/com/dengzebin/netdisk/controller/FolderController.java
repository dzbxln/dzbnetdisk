package com.dengzebin.netdisk.controller;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.dengzebin.netdisk.service.FolderService;
import com.dengzebin.netdisk.untils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengZebing
 * @Description TODO
 * @Date 2023/5/30 15:06
 */
@RestController
@RequestMapping("/netDisk")
public class FolderController {

    @Resource
    private FolderService folderService;

    @GetMapping("/get_folder_list")
    public Result getFolderList(@RequestParam("fId") String fId){
        List<Folder> folderList = new ArrayList<>();
        if ("".equals(fId))
            folderList = folderService.getFolderList(null);
        else
            folderList = folderService.getFolderList(fId);
        return Result.success(folderList);
    }

    @PostMapping("/create_folder")
    public Result createFolder(Folder folder) throws Exception{
        if (!folderService.createFolder(folder))
            throw new Exception("创建失败！");
        return Result.success();
    }
}
