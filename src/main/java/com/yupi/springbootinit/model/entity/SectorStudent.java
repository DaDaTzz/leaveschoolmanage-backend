package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 环节学生关联
 * @TableName sector_student
 * @author Da
 */
@TableName(value ="sector_student")
@Data
public class SectorStudent implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 环节id
     */
    private Long sectorId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 完成情况（0-未完成 1-已完成）
     */
    private Integer completionStatus;

    /**
     * 完成时间
     */
    private Date completionTime;

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