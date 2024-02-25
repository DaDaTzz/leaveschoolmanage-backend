package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.dto.leaveSchoolStudentInfo.LeaveSchoolStudentInfoQueryRequest;
import com.yupi.springbootinit.model.entity.LeaveSchoolStudentInfo;
import com.yupi.springbootinit.model.vo.leaveSchoolStudentInfo.LeaveSchoolStudentInfoVO;
import com.yupi.springbootinit.service.LeaveSchoolStudentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 离校学生信息接口
 *
 * @author Da
 */
@RestController
@RequestMapping("/leaveSchoolStudentInfo")
@Slf4j
public class LeaveSchoolStudentInfoController {

    @Resource
    private LeaveSchoolStudentInfoService leaveSchoolStudentInfoService;


    /**
     * 分页获取列表（封装类）
     *
     *
     * @param leaveSchoolStudentInfoQueryRequest 离校学生信息VO查询请求参数封装
     * @return  leaveSchoolStudentInfo VO
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<LeaveSchoolStudentInfoVO>> listLeaveSchoolStudentInfoVOByPage(@RequestBody LeaveSchoolStudentInfoQueryRequest leaveSchoolStudentInfoQueryRequest) {
        long current = leaveSchoolStudentInfoQueryRequest.getCurrent();
        long size = leaveSchoolStudentInfoQueryRequest.getPageSize();
        Page<LeaveSchoolStudentInfo> leaveSchoolStudentInfoPage = leaveSchoolStudentInfoService.page(new Page<>(current, size),
                leaveSchoolStudentInfoService.getQueryWrapper(leaveSchoolStudentInfoQueryRequest));
        return ResultUtils.success(leaveSchoolStudentInfoService.getLeaveSchoolStudentInfoVOPage(leaveSchoolStudentInfoPage));
    }



    /**
     * 根据 学号 获取
     *
     * @param studentId 学号
     * @return LeaveSchoolStudentInfoVO
     */
    @GetMapping("/get/vo")
    public BaseResponse<LeaveSchoolStudentInfoVO> getLeaveSchoolStudentInfoVOByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<LeaveSchoolStudentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LeaveSchoolStudentInfo::getStudentId, studentId);
        LeaveSchoolStudentInfo leaveSchoolStudentInfo = leaveSchoolStudentInfoService.getOne(queryWrapper);
        if (leaveSchoolStudentInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(leaveSchoolStudentInfoService.getLeaveSchoolStudentInfoVO(leaveSchoolStudentInfo));
    }





}
