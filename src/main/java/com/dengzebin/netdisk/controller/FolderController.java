package com.dengzebin.netdisk.controller;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.dengzebin.netdisk.service.FolderService;
import com.dengzebin.netdisk.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private FolderService folderService;

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    /**
     * 获取fId对应的文件夹里面的文件夹列表
     *
     * @param fId 文件夹ID
     * @return 文件夹信息列表
     */
    @GetMapping("/get_folder_list")
    public Result getFolderList(@RequestParam("fId") String fId) {
        List<Folder> folderList = new ArrayList<>();
        if ("".equals(fId))
            folderList = folderService.getFolderList(null);
        else
            folderList = folderService.getFolderList(fId);
        return Result.success(folderList);
    }

    /**
     * 创建文件夹
     *
     * @param folder 文件夹信息
     * @return 插入数据库后拿到fId的文件夹信息
     * @throws Exception 创建失败！
     */
    @PostMapping("/create_folder")
    public Result createFolder(@RequestBody Folder folder) throws Exception {
        if (!folderService.createFolder(folder))
            throw new Exception("创建失败！");
        return Result.success(folder);
    }

    /**
     * 更新文件夹信息
     *
     * @param folder 文件夹信息
     * @return 数据库更新后的文件夹信息
     * @throws Exception 修改失败！
     */
    @PutMapping("/update_folder")
    public Result updateFolder(@RequestBody Folder folder) throws Exception {
        folder.setUpdateTime(new Date());
        if (!folderService.updateFolder(folder))
            throw new Exception("修改失败！");
        return Result.success(folder);
    }

    /**
     * 删除对应的文件夹
     *
     * @param fId 文件夹ID
     * @return 成功
     * @throws Exception 删除失败！
     */
    @PostMapping("/delete_folder/{fId}")
    public Result deleteFolder(@PathVariable("fId") String fId) throws Exception {
        if (!folderService.deleteFolder(Long.valueOf(fId)))
            throw new Exception("删除失败！");
        return Result.success();
    }

    /**
     * 用于面包屑信息
     *
     * @param masterId 父文件夹id
     * @return 文件夹名和对应id
     */
    @GetMapping("/get_breadcrumb")
    public Result getBreadcrumb(@RequestParam("masterId") String masterId) {
        List<HashMap<String, String>> folderList = folderService.getBreadcrumb(masterId);
        return Result.success(folderList);
    }
}
