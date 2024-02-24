package com.yupi.springbootinit.model.dto.sector;

import com.yupi.springbootinit.model.dto.task.AddTaskRequest;
import lombok.Data;

import java.util.List;

/**
 * 新增环节请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/22 10:23
 */
@Data
public class AddSectorRequest {

    /**
     * 环节名称
     */
    private String name;

    /**
     * 环节描述
     */
    private String description;

    /**
     * 办理地点
     */
    private String doLocation;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 办理方式
     */
    private String doWay;

    /**
     * 办理时间
     */
    private String doTime;

    /**
     * 办理提示
     */
    private String doTip;

    /**
     * 环节任务
     */
    private List<AddTaskRequest> addTaskRequestList;


}
