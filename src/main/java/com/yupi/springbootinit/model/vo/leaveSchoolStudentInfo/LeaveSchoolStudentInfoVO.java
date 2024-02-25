package com.yupi.springbootinit.model.vo.leaveSchoolStudentInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yupi.springbootinit.model.entity.LeaveSchoolStudentInfo;
import com.yupi.springbootinit.model.vo.process.ProcessVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 离校学生信息VO
 * @TableName leave_school_student_info
 * @author Da
 */
@TableName(value ="leave_school_student_info")
@Data
public class LeaveSchoolStudentInfoVO implements Serializable {
    /**
     * id
     */
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
     * 学历id
     */
    private String schoolingId;

    /**
     * 学历名称
     */
    private String schoolingName;


    /**
     * 年级id
     */
    private String gradeId;


    /**
     * 年级名称
     */
    private String gradeName;


    /**
     * 流程id
     */
    private Long processId;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 学院id
     */
    private String collegeId;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 专业id
     */
    private String specialityId;

    /**
     * 专业名称
     */
    private String specialityName;

    /**
     * 班级id
     */
    private String clazzId;

    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 性别（0-未离校 1-离校）
     */
    private Integer leaveStatus;

    /**
     * 离校时间
     */
    private Date leaveSchoolTime;


    /**
     * 流程VO
     */
    private ProcessVO processVO;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 对象转包装类
     *
     * @param leaveSchoolStudentInfo
     * @return
     */
    public static LeaveSchoolStudentInfoVO objToVo(LeaveSchoolStudentInfo leaveSchoolStudentInfo) {
        if (leaveSchoolStudentInfo == null) {
            return null;
        }
        LeaveSchoolStudentInfoVO leaveSchoolStudentInfoVO = new LeaveSchoolStudentInfoVO();
        BeanUtils.copyProperties(leaveSchoolStudentInfo, leaveSchoolStudentInfoVO);
        return leaveSchoolStudentInfoVO;
    }

    /**
     * 包装类转对象
     *
     * @param leaveSchoolStudentInfoVO
     * @return
     */
    public static LeaveSchoolStudentInfo voToObj(LeaveSchoolStudentInfoVO leaveSchoolStudentInfoVO) {
        if (leaveSchoolStudentInfoVO == null) {
            return null;
        }
        LeaveSchoolStudentInfo leaveSchoolStudentInfo = new LeaveSchoolStudentInfo();
        BeanUtils.copyProperties(leaveSchoolStudentInfoVO, leaveSchoolStudentInfo);
        return leaveSchoolStudentInfo;
    }
}