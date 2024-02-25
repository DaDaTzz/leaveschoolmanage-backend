package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.dto.leaveSchoolStudentInfo.LeaveSchoolStudentInfoQueryRequest;
import com.yupi.springbootinit.model.entity.LeaveSchoolStudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.vo.leaveSchoolStudentInfo.LeaveSchoolStudentInfoVO;

/**
 * 离校学生信息服务
 * @author Da
 */
public interface LeaveSchoolStudentInfoService extends IService<LeaveSchoolStudentInfo> {

    Wrapper<LeaveSchoolStudentInfo> getQueryWrapper(LeaveSchoolStudentInfoQueryRequest leaveSchoolStudentInfoQueryRequest);

    Page<LeaveSchoolStudentInfoVO> getLeaveSchoolStudentInfoVOPage(Page<LeaveSchoolStudentInfo> leaveSchoolStudentInfoPage);

    LeaveSchoolStudentInfoVO getLeaveSchoolStudentInfoVO(LeaveSchoolStudentInfo leaveSchoolStudentInfo);
}
