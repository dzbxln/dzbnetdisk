package com.dengzebin.netdisk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dengzebin.netdisk.entity.DO.Folder;
import com.dengzebin.netdisk.service.FolderService;
import com.dengzebin.netdisk.mapper.FolderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder>
    implements FolderService{

    @Resource
    private FolderMapper folderMapper;

    @Override
    public List<Folder> getFolderList(String master_id) {
        return folderMapper.getFolderList(master_id);
    }

    @Override
    public boolean createFolder(Folder folder) {
        int res = folderMapper.insert(folder);
        return res > 0;
    }

    @Override
    public boolean updateFolder(Folder folder) {
        int res = folderMapper.updateById(folder);
        return res > 0;
    }

    @Override
    public boolean deleteFolder(Long fId) {
        LambdaUpdateWrapper<Folder> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Folder::getFId,fId).set(Folder::getIsDelete,1);
        int res = folderMapper.update(null,queryWrapper);
        return res > 0;
    }
}




