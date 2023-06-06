package com.dengzebin.netdisk.mapper;

import com.dengzebin.netdisk.entity.DO.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.dengzebin.netdisk.entity.DO.Folder
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

    /**
     * 查找父文件夹ID为master_id的文件夹
     * @param master_id 父文件夹id
     * @return 文件夹列表
     */
    List<Folder> getFolderList(@Param("master_id") String master_id);

}




