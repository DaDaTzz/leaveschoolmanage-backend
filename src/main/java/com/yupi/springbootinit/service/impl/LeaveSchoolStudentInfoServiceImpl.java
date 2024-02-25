package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.mapper.LeaveSchoolStudentInfoMapper;
import com.yupi.springbootinit.model.dto.leaveSchoolStudentInfo.LeaveSchoolStudentInfoQueryRequest;
import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.entity.*;
import com.yupi.springbootinit.model.vo.leaveSchoolStudentInfo.LeaveSchoolStudentInfoVO;
import com.yupi.springbootinit.model.vo.process.ProcessVO;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import com.yupi.springbootinit.model.vo.task.TaskVO;
import com.yupi.springbootinit.service.*;
import com.yupi.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 离校学生信息服务实现
 * @author Da
 */
@Service
public class LeaveSchoolStudentInfoServiceImpl extends ServiceImpl<LeaveSchoolStudentInfoMapper, LeaveSchoolStudentInfo>
    implements LeaveSchoolStudentInfoService{

    @Resource
    private SchoolingService schoolingService;
    @Resource
    private GradeService gradeService;
    @Resource
    private ProcessService processService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private SpecialityService specialityService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private SectorStudentService sectorStudentService;
    @Resource
    private TaskStudentService taskStudentService;

    @Override
    public Wrapper<LeaveSchoolStudentInfo> getQueryWrapper(LeaveSchoolStudentInfoQueryRequest leaveSchoolStudentInfoQueryRequest) {
        QueryWrapper<LeaveSchoolStudentInfo> queryWrapper = new QueryWrapper<>();
        if (leaveSchoolStudentInfoQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = leaveSchoolStudentInfoQueryRequest.getSearchText();
        Long id = leaveSchoolStudentInfoQueryRequest.getId();
        String studentId = leaveSchoolStudentInfoQueryRequest.getStudentId();
        String name = leaveSchoolStudentInfoQueryRequest.getName();
        Integer age = leaveSchoolStudentInfoQueryRequest.getAge();
        Integer sex = leaveSchoolStudentInfoQueryRequest.getSex();
        Date intakeTime = leaveSchoolStudentInfoQueryRequest.getIntakeTime();
        Date graduationTimeTime = leaveSchoolStudentInfoQueryRequest.getGraduationTimeTime();
        String schoolingId = leaveSchoolStudentInfoQueryRequest.getSchoolingId();
        String gradeId = leaveSchoolStudentInfoQueryRequest.getGradeId();
        Long processId = leaveSchoolStudentInfoQueryRequest.getProcessId();
        String collegeId = leaveSchoolStudentInfoQueryRequest.getCollegeId();
        String specialityId = leaveSchoolStudentInfoQueryRequest.getSpecialityId();
        String clazzId = leaveSchoolStudentInfoQueryRequest.getClazzId();
        Integer leaveStatus = leaveSchoolStudentInfoQueryRequest.getLeaveStatus();
        Date leaveSchoolTime = leaveSchoolStudentInfoQueryRequest.getLeaveSchoolTime();
        String sortField = leaveSchoolStudentInfoQueryRequest.getSortField();
        String sortOrder = leaveSchoolStudentInfoQueryRequest.getSortOrder();


        // 拼接查询条件
        if (org.apache.commons.lang3.StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("name", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(StringUtils.isNotBlank(studentId), "studentId", studentId);
        queryWrapper.eq(sex != null , "sex", sex);
        queryWrapper.eq(age != null , "age", age);
        queryWrapper.eq(schoolingId != null , "schoolingId", schoolingId);
        queryWrapper.eq(age != null , "age", age);
        queryWrapper.eq(gradeId != null , "gradeId", gradeId);
        queryWrapper.eq(processId != null , "processId", processId);
        queryWrapper.eq(collegeId != null , "collegeId", collegeId);
        queryWrapper.eq(specialityId != null , "specialityId", specialityId);
        queryWrapper.eq(leaveStatus != null , "leaveStatus", leaveStatus);
        queryWrapper.eq(id != null , "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(clazzId), "clazzId", clazzId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public Page<LeaveSchoolStudentInfoVO> getLeaveSchoolStudentInfoVOPage(Page<LeaveSchoolStudentInfo> leaveSchoolStudentInfoPage) {
        List<LeaveSchoolStudentInfo> leaveSchoolStudentInfoList = leaveSchoolStudentInfoPage.getRecords();
        Page<LeaveSchoolStudentInfoVO> leaveSchoolStudentInfoVOPage = new Page<>(leaveSchoolStudentInfoPage.getCurrent(), leaveSchoolStudentInfoPage.getSize(), leaveSchoolStudentInfoPage.getTotal());
        if (CollUtil.isEmpty(leaveSchoolStudentInfoList)) {
            return leaveSchoolStudentInfoVOPage;
        }
        // 填充信息
        List<LeaveSchoolStudentInfoVO> leaveSchoolStudentInfoVOList = new ArrayList<>();
        leaveSchoolStudentInfoList.forEach(leaveSchoolStudentInfo -> {
            LeaveSchoolStudentInfoVO leaveSchoolStudentInfoVO = new LeaveSchoolStudentInfoVO();
            leaveSchoolStudentInfoVO = fillLeaveSchoolStudentInfoVOName(leaveSchoolStudentInfoVO, leaveSchoolStudentInfo);
            BeanUtil.copyProperties(leaveSchoolStudentInfo, leaveSchoolStudentInfoVO);
            leaveSchoolStudentInfoVOList.add(leaveSchoolStudentInfoVO);
        });
        leaveSchoolStudentInfoVOPage.setRecords(leaveSchoolStudentInfoVOList);
        return leaveSchoolStudentInfoVOPage;
    }



    @Override
    public LeaveSchoolStudentInfoVO getLeaveSchoolStudentInfoVO(LeaveSchoolStudentInfo leaveSchoolStudentInfo) {
        LeaveSchoolStudentInfoVO leaveSchoolStudentInfoVO = LeaveSchoolStudentInfoVO.objToVo(leaveSchoolStudentInfo);
        // 1. 填充名称
        leaveSchoolStudentInfoVO = fillLeaveSchoolStudentInfoVOName(leaveSchoolStudentInfoVO, leaveSchoolStudentInfo);
        // 2. 填充环节、任务
        Process process = processService.getById(leaveSchoolStudentInfoVO.getProcessId());
        ProcessVO processVO = processService.getProcessVO(process);
        List<SectorVO> sectorVOList = processVO.getSectorVOList();
        // 设置环节完成时间和完成状态
        for (SectorVO sectorVO : sectorVOList) {
            LambdaQueryWrapper<SectorStudent> sectorStudentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sectorStudentLambdaQueryWrapper.eq(SectorStudent::getSectorId, sectorVO.getId()).eq(SectorStudent::getStudentId, leaveSchoolStudentInfoVO.getStudentId());
            SectorStudent sectorStudent = sectorStudentService.getOne(sectorStudentLambdaQueryWrapper);
            sectorVO.setCompletionStatus(sectorStudent.getCompletionStatus());
            sectorVO.setCompletionTime(sectorStudent.getCompletionTime());
            // 设置任务完成时间和完成状态
            List<TaskVO> taskVOList = sectorVO.getTaskVOList();
            for (TaskVO taskVO : taskVOList) {
                LambdaQueryWrapper<TaskStudent> taskStudentLambdaQueryWrapper = new LambdaQueryWrapper<>();
                taskStudentLambdaQueryWrapper.eq(TaskStudent::getTaskId, taskVO.getId()).eq(TaskStudent::getStudentId, leaveSchoolStudentInfoVO.getStudentId());
                TaskStudent taskStudent = taskStudentService.getOne(taskStudentLambdaQueryWrapper);
                taskVO.setCompletionStatus(taskStudent.getCompletionStatus());
                taskVO.setCompletionTime(taskStudent.getCompletionTime());
            }
        }
        leaveSchoolStudentInfoVO.setProcessVO(processVO);
        return leaveSchoolStudentInfoVO;
    }





    public  LeaveSchoolStudentInfoVO fillLeaveSchoolStudentInfoVOName(LeaveSchoolStudentInfoVO leaveSchoolStudentInfoVO, LeaveSchoolStudentInfo leaveSchoolStudentInfo){
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        collegeLambdaQueryWrapper.eq(College::getId, leaveSchoolStudentInfo.getCollegeId());
        leaveSchoolStudentInfoVO.setCollegeName(collegeService.getOne(collegeLambdaQueryWrapper).getName());
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getId, leaveSchoolStudentInfo.getGradeId());
        leaveSchoolStudentInfoVO.setGradeName(gradeService.getOne(gradeLambdaQueryWrapper).getName());
        LambdaQueryWrapper<Process> processLambdaQueryWrapper = new LambdaQueryWrapper<>();
        processLambdaQueryWrapper.eq(Process::getId, leaveSchoolStudentInfo.getProcessId());
        leaveSchoolStudentInfoVO.setProcessName(processService.getOne(processLambdaQueryWrapper).getName());
        LambdaQueryWrapper<Speciality> specialityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        specialityLambdaQueryWrapper.eq(Speciality::getId, leaveSchoolStudentInfo.getSpecialityId());
        leaveSchoolStudentInfoVO.setSpecialityName(specialityService.getOne(specialityLambdaQueryWrapper).getName());
        LambdaQueryWrapper<Schooling> schoolingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        schoolingLambdaQueryWrapper.eq(Schooling::getId, leaveSchoolStudentInfo.getSchoolingId());
        leaveSchoolStudentInfoVO.setSchoolingName(schoolingService.getOne(schoolingLambdaQueryWrapper).getName());
        LambdaQueryWrapper<Clazz> clazzLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clazzLambdaQueryWrapper.eq(Clazz::getId, leaveSchoolStudentInfo.getClazzId());
        leaveSchoolStudentInfoVO.setClazzName(clazzService.getOne(clazzLambdaQueryWrapper).getName());
        return leaveSchoolStudentInfoVO;
    }
}




