package com.dengzebin.netdisk.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName folder
 */
@TableName(value ="folder")
@Data
public class Folder implements Serializable {
    /**
     * 
     */
    @TableId
    private Long fId;

    /**
     * 文件夹名字
     */
    private String folderName;

    /**
     * 父文件夹id
     */
    private Long masterFolderId;

    /**
     * 存储大小
     */
    private BigDecimal volume;

    /**
     * 文件夹图片
     */
    private String image;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除，0：未删除，1：删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}