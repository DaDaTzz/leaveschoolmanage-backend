package com.yupi.springbootinit.model.dto.process;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

/**
 * 流程查询请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/22 18:16
 */
@Data
public class ProcessQueryRequest extends PageRequest {

    /**
     * 搜索词
     */
    private String searchText;

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
