package com.dengzebin.netdisk.mapper;

import com.dengzebin.netdisk.entity.DO.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.dengzebin.netdisk.entity.DO.File
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    List<File> getFileList(@Param("masterId") String masterId);
}




