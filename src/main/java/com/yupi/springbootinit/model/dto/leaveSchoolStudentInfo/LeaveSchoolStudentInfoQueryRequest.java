package com.yupi.springbootinit.model.dto.leaveSchoolStudentInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 新增老师请求参数封装
 * @author Da
 * @since 2024-02-25 12:51
 */
@Data
public class LeaveSchoolStudentInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * 搜索词
     */
    private String searchText;

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
    private String schoolingId;

    /**
     * 年级
     */
    private String gradeId;

    /**
     * 流程id
     */
    private Long processId;

    /**
     * 学院id
     */
    private String collegeId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
