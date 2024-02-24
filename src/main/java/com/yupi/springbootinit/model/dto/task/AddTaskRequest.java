package com.yupi.springbootinit.model.dto.task;

import lombok.Data;

/**
 * 新增任务请求
 * @author Da
 * @version 1.0
 * @date 2024/2/22 10:23
 */
@Data
public class AddTaskRequest {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String description;


    /**
     * 是否必须（0-必须 1-非必须）
     */
    private String isMust;

}
