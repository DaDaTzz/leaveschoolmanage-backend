package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Teacher;
import com.yupi.springbootinit.service.TeacherService;
import com.yupi.springbootinit.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
 * 老师服务实现
 * @author Da
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




