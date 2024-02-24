package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程
 * @TableName process
 * @author Da
 */
@TableName(value ="process")
@Data
public class Process implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 年度
     */
    private String year;

    /**
     * 描述
     */
    private String description;

    /**
     * 离校类型（0-正常离校 1-非正常离校）
     */
    private Integer leaveType;

    /**
     * 状态（0-关闭 1-开启）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}