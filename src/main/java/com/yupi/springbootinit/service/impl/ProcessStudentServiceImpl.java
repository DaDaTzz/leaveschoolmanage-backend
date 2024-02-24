package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.ProcessStudent;
import com.yupi.springbootinit.service.ProcessStudentService;
import com.yupi.springbootinit.mapper.ProcessStudentMapper;
import org.springframework.stereotype.Service;

/**
 * 流程学生关联服务实现
 * @author Da
 */
@Service
public class ProcessStudentServiceImpl extends ServiceImpl<ProcessStudentMapper, ProcessStudent>
    implements ProcessStudentService{

}




