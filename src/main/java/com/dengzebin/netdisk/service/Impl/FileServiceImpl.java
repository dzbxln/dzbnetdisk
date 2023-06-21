package com.dengzebin.netdisk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dengzebin.netdisk.entity.DO.File;
import com.dengzebin.netdisk.service.FileService;
import com.dengzebin.netdisk.mapper.FileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
        implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public boolean createFile(File file) {
        int res = fileMapper.insert(file);
        return res > 0;
    }

    @Override
    public List<File> getFileList(String masterId) {
        return fileMapper.getFileList(masterId);
    }

    @Override
    public boolean deleteFile(String fileId) {
        int res = fileMapper.deleteById(fileId);
        return res > 0;
    }
}




