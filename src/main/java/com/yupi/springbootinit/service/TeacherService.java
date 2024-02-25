package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.teacher.TeacherQueryRequest;
import com.yupi.springbootinit.model.entity.Teacher;
import com.yupi.springbootinit.model.vo.teacher.TeacherVO;

/**
 * 老师服务
 * @author Da
 */
public interface TeacherService extends IService<Teacher> {


    void validTeacher(Teacher teacher, boolean add);

    Wrapper<Teacher> getQueryWrapper(TeacherQueryRequest teacherQueryRequest);

    Page<TeacherVO> getTeacherVOPage(Page<Teacher> teacherPage);
}
