package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.SectorStudent;
import com.yupi.springbootinit.service.SectorStudentService;
import com.yupi.springbootinit.mapper.SectorStudentMapper;
import org.springframework.stereotype.Service;

/**
 * 环节学生关联服务实现
 * @author Da
 */
@Service
public class SectorStudentServiceImpl extends ServiceImpl<SectorStudentMapper, SectorStudent>
    implements SectorStudentService{

}




