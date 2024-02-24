package com.yupi.springbootinit.model.dto.process;

import lombok.Data;

/**
 * 更新流程请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/22 10:23
 */
@Data
public class UpdateProcessRequest {


    /**
     * id
     */
    private Long id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 年度
     */
    private String year;

    /**
     * 描述
     */
    private String description;

    /**
     * 离校类型（0-正常离校 1-非正常离校）
     */
    private Integer leaveType;


    /**
     * 状态（0-关闭 1-开启）
     */
    private Integer status;

}
