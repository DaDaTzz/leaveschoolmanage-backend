package com.yupi.springbootinit.model.dto.processSector;

import lombok.Data;

/**
 * 更新流程环节是否必须请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/23 15:01
 */
@Data
public class UpdateSectorIsMustRequest {


    /**
     * 环节 id
     */
    private Long sectorId;

    /**
     * 流程 id
     */
    private Long processId;


    /**
     * 是否必须
     */
    private Integer isMust;
}
