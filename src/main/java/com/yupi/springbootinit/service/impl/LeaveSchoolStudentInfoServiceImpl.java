package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.LeaveSchoolStudentInfo;
import com.yupi.springbootinit.service.LeaveSchoolStudentInfoService;
import com.yupi.springbootinit.mapper.LeaveSchoolStudentInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 离校学生信息服务实现
 * @author Da
 */
@Service
public class LeaveSchoolStudentInfoServiceImpl extends ServiceImpl<LeaveSchoolStudentInfoMapper, LeaveSchoolStudentInfo>
    implements LeaveSchoolStudentInfoService{

}




