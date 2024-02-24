package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 离校学生信息
 * @TableName leave_school_student_info
 * @author Da
 */
@TableName(value ="leave_school_student_info")
@Data
public class LeaveSchoolStudentInfo implements Serializable {
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
     * 年龄
     */
    private Integer age;

    /**
     * 性别（0-女 1-男）
     */
    private Integer sex;

    /**
     * 入学时间
     */
    private Date intakeTime;

    /**
     * 毕业时间
     */
    private Date graduationTimeTime;

    /**
     * 学历
     */
    private String education;

    /**
     * 年级
     */
    private String grade;

    /**
     * 学院id
     */
    private String departmentId;

    /**
     * 专业id
     */
    private String specialityId;

    /**
     * 班级id
     */
    private String clazzId;

    /**
     * 性别（0-未离校 1-离校）
     */
    private Integer leaveStatus;

    /**
     * 离校时间
     */
    private Date leaveSchoolTime;

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