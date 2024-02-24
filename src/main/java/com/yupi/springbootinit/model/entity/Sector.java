package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 环节
 * @TableName sector
 * @author Da
 */
@TableName(value ="sector")
@Data
public class Sector implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 环节名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 办理地点
     */
    private String doLocation;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 办理时间
     */
    private String doTime;

    /**
     * 办理提示
     */
    private String doTip;

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