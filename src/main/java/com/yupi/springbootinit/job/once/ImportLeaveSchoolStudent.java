package com.yupi.springbootinit.job.once;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.entity.*;
import com.yupi.springbootinit.model.vo.process.ProcessVO;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import com.yupi.springbootinit.model.vo.task.TaskVO;
import com.yupi.springbootinit.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  导入离校学生信息
 * @author Da
 * @since 2024-02-25 15:14
 */
@Component
@Slf4j
public class ImportLeaveSchoolStudent implements CommandLineRunner {

    @Resource
    private StudentService studentService;
    @Resource
    private LeaveSchoolStudentInfoService leaveSchoolStudentInfoService;
    @Resource
    private ProcessService processService;
    @Resource
    private ProcessStudentService processStudentService;
    @Resource
    private SectorStudentService sectorStudentService;
    @Resource
    private TaskStudentService taskStudentService;

    @Transactional
    public void run(String... args){
        List<Student> studentList = studentService.list();
        if (CollUtil.isEmpty(studentList)) {
            return;
        }
        ArrayList<LeaveSchoolStudentInfo> schoolStudentInfoArrayList = new ArrayList<>();
        ArrayList<SectorStudent> sectorStudentList = new ArrayList<>();
        ArrayList<TaskStudent> taskStudentList = new ArrayList<>();
        for (Student student : studentList) {
            LambdaQueryWrapper<LeaveSchoolStudentInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(LeaveSchoolStudentInfo::getStudentId, student.getStudentId());
            // 如果存在则不重复添加
            if (leaveSchoolStudentInfoService.getOne(queryWrapper) != null) {
                continue;
            }
            LeaveSchoolStudentInfo leaveSchoolStudentInfo = getLeaveSchoolStudentInfo(student);
            if(StringUtils.isNotBlank(leaveSchoolStudentInfo.getStudentId())){
                schoolStudentInfoArrayList.add(leaveSchoolStudentInfo);
                // 建立学生流程关系
                if(leaveSchoolStudentInfo.getProcessId() != null){
                    ProcessStudent processStudent = new ProcessStudent();
                    processStudent.setProcessId(leaveSchoolStudentInfo.getProcessId());
                    processStudent.setStudentId(Long.valueOf(leaveSchoolStudentInfo.getStudentId()));
                    processStudentService.save(processStudent);
                    ProcessVO processVO = processService.getProcessVO(processService.getById(leaveSchoolStudentInfo.getProcessId()));
                    List<SectorVO> sectorVOList = processVO.getSectorVOList();
                    // 建立学生环节关系
                    sectorVOList.forEach(sectorVO -> {
                        SectorStudent sectorStudent = new SectorStudent();
                        sectorStudent.setSectorId(sectorVO.getId());
                        sectorStudent.setStudentId(Long.valueOf(leaveSchoolStudentInfo.getStudentId()));
                        sectorStudentList.add(sectorStudent);
                        // 建立学生任务关系
                        List<TaskVO> taskVOList = sectorVO.getTaskVOList();
                        taskVOList.forEach(taskVO -> {
                            TaskStudent taskStudent = new TaskStudent();
                            taskStudent.setTaskId(taskVO.getId());
                            taskStudent.setStudentId(Long.valueOf(leaveSchoolStudentInfo.getStudentId()));
                            taskStudentList.add(taskStudent);
                        });
                    });
                }
            }
        }
        leaveSchoolStudentInfoService.saveBatch(schoolStudentInfoArrayList);
        sectorStudentService.saveBatch(sectorStudentList);
        taskStudentService.saveBatch(taskStudentList);
        log.info("ImportLeaveSchoolStudent end, total {}", schoolStudentInfoArrayList.size());




    }

    @NotNull
    private  LeaveSchoolStudentInfo getLeaveSchoolStudentInfo(Student student) {
        LeaveSchoolStudentInfo leaveSchoolStudentInfo = new LeaveSchoolStudentInfo();
        leaveSchoolStudentInfo.setStudentId(student.getStudentId());
        leaveSchoolStudentInfo.setName(student.getName());
        leaveSchoolStudentInfo.setAge(student.getAge());
        leaveSchoolStudentInfo.setSex(student.getSex());
        leaveSchoolStudentInfo.setIntakeTime(student.getIntakeTime());
        leaveSchoolStudentInfo.setGraduationTimeTime(student.getGraduationTime());
        leaveSchoolStudentInfo.setSchoolingId(String.valueOf(student.getSchoolingId()));
        leaveSchoolStudentInfo.setGradeId(String.valueOf(student.getGradeId()));
        leaveSchoolStudentInfo.setCollegeId(String.valueOf(student.getCollegeId()));
        leaveSchoolStudentInfo.setSpecialityId(String.valueOf(student.getSpecialityId()));
        leaveSchoolStudentInfo.setClazzId(String.valueOf(student.getClazzId()));
        Date graduationTime = student.getGraduationTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String graduationYear = format.format(graduationTime);
        LambdaQueryWrapper<Process> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Process::getYear, graduationYear);
        List<Process> processList = processService.list(queryWrapper);
        if(processList == null || processList.isEmpty()){
            return new LeaveSchoolStudentInfo();
        }
        leaveSchoolStudentInfo.setProcessId(processList.get(0).getId());
        return leaveSchoolStudentInfo;
    }
}
