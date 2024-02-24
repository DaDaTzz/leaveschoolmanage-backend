package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Student;
import com.yupi.springbootinit.service.StudentService;
import com.yupi.springbootinit.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 * 学生服务实现
 * @author Da
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




