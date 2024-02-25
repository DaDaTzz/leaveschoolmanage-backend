package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.TeacherMapper;
import com.yupi.springbootinit.model.dto.teacher.TeacherQueryRequest;
import com.yupi.springbootinit.model.entity.Clazz;
import com.yupi.springbootinit.model.entity.Department;
import com.yupi.springbootinit.model.entity.Teacher;
import com.yupi.springbootinit.model.vo.teacher.TeacherVO;
import com.yupi.springbootinit.service.ClazzService;
import com.yupi.springbootinit.service.DepartmentService;
import com.yupi.springbootinit.service.TeacherService;
import com.yupi.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 老师服务实现
 * @author Da
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{
    @Resource
    private DepartmentService departmentService;
    @Resource
    private ClazzService clazzService;


    @Override
    public void validTeacher(Teacher teacher, boolean add) {
        ThrowUtils.throwIf(teacher == null, ErrorCode.PARAMS_ERROR, "任务为空");
        String teacherId = teacher.getTeacherId();
        String name = teacher.getName();
        Integer sex = teacher.getSex();
        Integer age = teacher.getAge();
        Date doWorkTime = teacher.getDoWorkTime();
        Long departmentId = teacher.getDepartmentId();
        String clazzId = teacher.getClazzId();

        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 50, ErrorCode.PARAMS_ERROR, "老师姓名过长");
        if (add) {
            // 1、参数不能为空
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, teacherId, clazzId) || age == null || doWorkTime == null || departmentId == null , ErrorCode.PARAMS_ERROR, "请求参数为空");
            // 2、查询部门id和班级id是否存在
            Department department = departmentService.getById(departmentId);
            ThrowUtils.throwIf(department == null, ErrorCode.PARAMS_ERROR, "部门不存在");
            Gson gson = new Gson();
            List<String> clazzIdList = Arrays.asList(gson.fromJson(clazzId, String[].class));
            List<Clazz> clazzList = clazzService.listByIds(clazzIdList);
            ThrowUtils.throwIf( clazzList == null || clazzList.isEmpty(), ErrorCode.PARAMS_ERROR, "班级不存在");
        }
    }

    @Override
    public Wrapper<Teacher> getQueryWrapper(TeacherQueryRequest teacherQueryRequest) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (teacherQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = teacherQueryRequest.getSearchText();
        String id = teacherQueryRequest.getId();
        String teacherId = teacherQueryRequest.getTeacherId();
        String name = teacherQueryRequest.getName();
        Integer sex = teacherQueryRequest.getSex();
        Integer age = teacherQueryRequest.getAge();
        Date doWorkTime = teacherQueryRequest.getDoWorkTime();
        Long departmentId = teacherQueryRequest.getDepartmentId();
        String clazzId = teacherQueryRequest.getClazzId();
        String sortField = teacherQueryRequest.getSortField();
        String sortOrder = teacherQueryRequest.getSortOrder();

        // 拼接查询条件
        if (org.apache.commons.lang3.StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("name", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(StringUtils.isNotBlank(teacherId), "teacherId", teacherId);
        queryWrapper.eq(sex != null , "sex", sex);
        queryWrapper.eq(departmentId != null , "departmentId", departmentId);
        queryWrapper.eq(StringUtils.isNotBlank(clazzId), "clazzId", clazzId);
        queryWrapper.eq(StringUtils.isNotBlank(id), "id", id);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public Page<TeacherVO> getTeacherVOPage(Page<Teacher> teacherPage) {
        List<Teacher> teacherList = teacherPage.getRecords();
        Page<TeacherVO> teacherVOPage = new Page<>(teacherPage.getCurrent(), teacherPage.getSize(), teacherPage.getTotal());
        if (CollUtil.isEmpty(teacherList)) {
            return teacherVOPage;
        }
        // 填充信息
        List<TeacherVO> teacherVOList = new ArrayList<>();
        teacherList.forEach(teacher -> {
            TeacherVO teacherVO = new TeacherVO();
            BeanUtil.copyProperties(teacher, teacherVO);
            teacherVO.setDepartmentName(departmentService.getById(teacher.getDepartmentId()).getName());
            Gson gson = new Gson();
            List<String> clazzIdList = Arrays.asList(gson.fromJson(teacher.getClazzId(), String[].class));
            List<Clazz> clazzList = clazzService.listByIds(clazzIdList);
            ArrayList<String> clazzNameList = new ArrayList<>();
            clazzList.forEach(clazz -> {
                clazzNameList.add(clazz.getName());
            });
            String clazzNameGsonStr = gson.toJson(clazzNameList);
            teacherVO.setClazzName(clazzNameGsonStr);
            teacherVOList.add(teacherVO);
        });
        teacherVOPage.setRecords(teacherVOList);
        return teacherVOPage;
    }
}




