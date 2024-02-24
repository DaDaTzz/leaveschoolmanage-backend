package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Department;
import com.yupi.springbootinit.service.DepartmentService;
import com.yupi.springbootinit.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
 * 部门服务实现
 * @author Da
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




