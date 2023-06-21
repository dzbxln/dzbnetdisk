package com.dengzebin.netdisk.service;

import com.dengzebin.netdisk.entity.DO.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface FileService extends IService<File> {

    /**
     * 新增文件
     *
     * @param file 文件数据
     * @return
     */
    boolean createFile(File file);

    /**
     * 查找文件列表
     *
     * @param masterId 父文件夹id
     * @return
     */
    List<File> getFileList(String masterId);

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    boolean deleteFile(String fileId);
}
