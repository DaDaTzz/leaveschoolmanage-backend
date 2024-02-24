package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 老师
 * @TableName teacher
 * @author Da
 */
@TableName(value ="teacher")
@Data
public class Teacher implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 工号
     */
    private String teacherId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0-女 1-男）
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 入职时间
     */
    private Date doWorkTime;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 班级id
     */
    private Long clazzId;

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