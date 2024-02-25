package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.TaskStudent;
import com.yupi.springbootinit.service.TaskStudentService;
import com.yupi.springbootinit.mapper.TaskStudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 13491
* @description 针对表【task_student(任务学生关联)】的数据库操作Service实现
* @createDate 2024-02-25 17:27:08
*/
@Service
public class TaskStudentServiceImpl extends ServiceImpl<TaskStudentMapper, TaskStudent>
    implements TaskStudentService{

}




