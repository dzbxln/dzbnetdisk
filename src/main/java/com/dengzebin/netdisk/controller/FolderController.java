package com.dengzebin.netdisk.controller;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.dengzebin.netdisk.service.FolderService;
import com.dengzebin.netdisk.untils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    public Result createFolder(@RequestBody Folder folder) throws Exception{
        if (!folderService.createFolder(folder))
            throw new Exception("创建失败！");
        return Result.success(folder);
    }

    @PutMapping("/update_folder")
    public Result updateFolder(@RequestBody Folder folder) throws Exception{
        folder.setUpdateTime(new Date());
        if (!folderService.updateFolder(folder))
            throw new Exception("修改失败！");
        return Result.success(folder);
    }

    @PostMapping("/delete_folder/{fId}")
    public Result deleteFolder(@PathVariable("fId") String fId) throws Exception {
        if (!folderService.deleteFolder(Long.valueOf(fId)))
            throw new Exception("删除失败！");
        return Result.success();
    }

    @GetMapping("/get_breadcrumb")
    public Result getBreadcrumb(@RequestParam("masterId") String masterId){
        List<HashMap<String, String>> folderList = folderService.getBreadcrumb(masterId);
        return Result.success(folderList);
    }
}
