package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.TaskMapper;
import com.yupi.springbootinit.model.entity.Task;
import com.yupi.springbootinit.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 任务服务实现
 * @author Da
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

    @Override
    public void validTask(Task task, boolean add) {
        ThrowUtils.throwIf(task == null, ErrorCode.PARAMS_ERROR, "任务为空");
        String name = task.getName();
        String description = task.getDescription();
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 255, ErrorCode.PARAMS_ERROR, "任务名称过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(description) && description.length() > 2000, ErrorCode.PARAMS_ERROR, "任务描述过长");

        if (add) {
            // 1、参数不能为空
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description), ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
    }

}




