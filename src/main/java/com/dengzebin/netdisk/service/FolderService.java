package com.dengzebin.netdisk.service;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dengzebin.netdisk.mapper.FolderMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
public interface FolderService extends IService<Folder> {

    List<Folder> getFolderList(String master_id);

    boolean createFolder(Folder folder);
}
