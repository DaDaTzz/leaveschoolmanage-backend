package com.yupi.springbootinit.model.vo.teacher;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yupi.springbootinit.model.entity.Teacher;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 老师VO
 *
 * @author Da
 */
@Data
public class TeacherVO implements Serializable {

    public static final long serialVersionUID = 1L;

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
    private String clazzId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 班级名称
     */
    private String clazzName;

    

    /**
     * 对象转包装类
     *
     * @param teacher
     * @return
     */
    public static TeacherVO objToVo(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacher, teacherVO);
        return teacherVO;
    }

    /**
     * 包装类转对象
     *
     * @param teacherVO
     * @return
     */
    public static Teacher voToObj(TeacherVO teacherVO) {
        if (teacherVO == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherVO, teacher);
        return teacher;
    }
}
