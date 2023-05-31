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

    List<Folder> getFolderList(@Param("master_id") String master_id);

}




