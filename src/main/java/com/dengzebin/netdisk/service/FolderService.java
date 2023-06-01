package com.dengzebin.netdisk.service;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dengzebin.netdisk.mapper.FolderMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface FolderService extends IService<Folder> {

    /**
     * 获取文件夹列表
     * @param master_id 父文件夹id（若为空则是根目录）
     * @return
     */
    List<Folder> getFolderList(String master_id);

    /**
     * 新增文件夹
     * @param folder 文件夹数据
     * @return
     */
    boolean createFolder(Folder folder);

    /**
     * 修改文件夹
     * @param folder 文件夹数据
     * @return
     */
    boolean updateFolder(Folder folder);

    /**
     * 删除文件夹
     * @param fId 文件夹id
     * @return
     */
    boolean deleteFolder(Long fId);

    /**
     * 面包屑导航数据
     * @param masterId 父文件夹id
     * @return
     */
    List<HashMap<String,String>> getBreadcrumb(String masterId);
}
