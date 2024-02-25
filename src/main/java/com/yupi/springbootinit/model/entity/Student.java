package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生
 * @TableName student
 * @author Da
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 学号
     */
    private String studentId;

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
     * 入学时间
     */
    private Date intakeTime;

    /**
     * 毕业时间
     */
    private Date graduationTime;

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 学历id
     */
    private Long schoolingId;

    /**
     * 学院id
     */
    private Long collegeId;

    /**
     * 专业id
     */
    private Long specialityId;

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