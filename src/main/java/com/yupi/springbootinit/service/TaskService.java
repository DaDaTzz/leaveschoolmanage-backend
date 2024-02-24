package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 任务服务
 * @author Da
 */
public interface TaskService extends IService<Task> {

    /**
     * 校验
     * @param task
     * @param add
     */
    void validTask(Task task, boolean add);
}
