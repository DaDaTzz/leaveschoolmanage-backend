package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.teacher.TeacherAddRequest;
import com.yupi.springbootinit.model.dto.teacher.TeacherQueryRequest;
import com.yupi.springbootinit.model.entity.Teacher;
import com.yupi.springbootinit.model.vo.teacher.TeacherVO;
import com.yupi.springbootinit.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 老师接口
 * @author Da
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 创建
     *
     * @param teacherAddRequest 老师添加请求参数封装
     * @return 新增老师 id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addTeacher(@RequestBody TeacherAddRequest teacherAddRequest) {
        if (teacherAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Teacher teacher = new Teacher();
        Gson gson = new Gson();
        String clazzJson = gson.toJson(teacherAddRequest.getClazzId());
        BeanUtils.copyProperties(teacherAddRequest, teacher);
        teacher.setClazzId(clazzJson);
        teacherService.validTeacher(teacher, true);
        boolean result = teacherService.save(teacher);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newTeacherId = teacher.getId();
        return ResultUtils.success(newTeacherId);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param teacherQueryRequest 老师查询请求参数封装
     * @return teacher
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Teacher>> listTeacherByPage(@RequestBody TeacherQueryRequest teacherQueryRequest) {
        long current = teacherQueryRequest.getCurrent();
        long size = teacherQueryRequest.getPageSize();
        Page<Teacher> teacherPage = teacherService.page(new Page<>(current, size),
                teacherService.getQueryWrapper(teacherQueryRequest));
        return ResultUtils.success(teacherPage);
    }



    /**
     * 分页获取列表（封装类）
     *
     * @param teacherQueryRequest 老师VO查询请求参数封装
     * @return  teacher VO
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<TeacherVO>> listTeacherVOByPage(@RequestBody TeacherQueryRequest teacherQueryRequest) {
        long current = teacherQueryRequest.getCurrent();
        long size = teacherQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Teacher> teacherPage = teacherService.page(new Page<>(current, size),
                teacherService.getQueryWrapper(teacherQueryRequest));
        return ResultUtils.success(teacherService.getTeacherVOPage(teacherPage));
    }

}
