package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程环节关联
 * @TableName process_sector
 * @author Da
 */
@TableName(value ="process_sector")
@Data
public class ProcessSector implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 流程id
     */
    private Long processId;

    /**
     * 环节id
     */
    private Long sectorId;

    /**
     * 是否必须（0-非必须 1-必须）
     */
    private Integer isMust;

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