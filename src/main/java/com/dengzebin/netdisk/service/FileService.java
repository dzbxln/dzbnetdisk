package com.dengzebin.netdisk.service;

import com.dengzebin.netdisk.entity.DO.File;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface FileService extends IService<File> {

    /**
     * 新增文件
     * @param file 文件数据
     * @return
     */
    boolean createFile(File file);
}
