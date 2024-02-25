package com.yupi.springbootinit.model.dto.teacher;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 新增老师请求参数封装
 * @author Da
 * @since 2024-02-25 12:51
 */
@Data
public class TeacherAddRequest implements Serializable {

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
    private List<String> clazzId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
